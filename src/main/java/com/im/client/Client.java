package com.im.client;


import com.im.client.handler.LoginResponseHandler;
import com.im.client.handler.MessageResponseHandler;
import com.im.codec.PacketDecoder;
import com.im.codec.PacketEncoder;
import com.im.protocol.Spliter;
import com.im.protocol.packet.request.LoginRequestPacket;
import com.im.protocol.packet.request.MessageRequestPacket;
import com.im.utils.LoginUtil;
import com.im.utils.UserSessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @program: im-netty->Client
 * @description: TODO
 * @author: huangfu
 * @date: 2019/12/2 14:01
 **/
public class Client {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>( ) {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new Spliter());

                        socketChannel.pipeline().addLast(new PacketDecoder());

                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());

                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(System.currentTimeMillis() + ": 连接成功!");
                Channel channel = ((ChannelFuture) future).channel( );
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(System.currentTimeMillis() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel){
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(() ->{
            while (!Thread.interrupted()){
                if (!UserSessionUtil.hasLogin(channel)) {
                    System.out.println("请输入您的用户id:" );
                    String userId = sc.nextLine( );
                    System.out.println("请输入您的用户名:" );
                    String userName = sc.nextLine( );
                    loginRequestPacket.setUserName(userName);
                    loginRequestPacket.setUserId(userId);
                    //发送登录请求
                    channel.writeAndFlush(loginRequestPacket);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace( );
                    }
                }else{
                    System.out.println("请输入您要发送给谁(用户id)：" );
                    String userId = sc.nextLine();
                    System.out.println("请输入您要发送的内容:" );
                    String message = sc.nextLine();
                    channel.writeAndFlush(new MessageRequestPacket(userId,message));
                }
            }
        }).start();
    }
}
