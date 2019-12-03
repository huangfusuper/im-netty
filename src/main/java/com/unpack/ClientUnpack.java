package com.unpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.concurrent.TimeUnit;

/**
 * @program: im-netty->ClientUnpack
 * @description: 客户端 处理粘包拆包
 * @author: huangfu
 * @date: 2019/12/3 17:11
 **/
public class ClientUnpack {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup work = new NioEventLoopGroup();

        bootstrap.group(work)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>( ) {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                /**
                 * LengthFieldBasedFrameDecoder
                 * 第一个参数：包的最大长度
                 * 第二个参数：字节偏移量，就是跳过多少字节才是长度所在协议
                 * 第三个参数：长度域的长度
                 * 第四各参数：//TODO
                 * 第五个参数：跳过多少字节，就是跳过字节长度域
                 */
                socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                socketChannel.pipeline( ).addLast(new ClientHandler( ));
            }
        });
        bootstrap.connect(HOST,PORT);
    }

    /*private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
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
    }*/
}
