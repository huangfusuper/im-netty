package com.im.server;

import com.im.codec.PacketDecoder;
import com.im.codec.PacketEncoder;
import com.im.protocol.Spliter;
import com.im.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @program: im-netty->Server
 * @description: 登陆服务端
 * @author: huangfu
 * @date: 2019/12/2 14:20
 **/
public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>( ) {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //socketChannel.pipeline().addLast(new LifecycleHandler())
                        socketChannel.pipeline().addLast(new Spliter());

                        //先对字节缓冲进行解码操作
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        //进行数据操作
                        socketChannel.pipeline().addLast(new LoginRequestHandler());
                        /**
                         * 做数据校验  这里有一个问题：
                         *      如果用户第一次登录成功了，那么后续99次操作，都需要验证，这是一种资源的浪费
                         *      所以我们需要使用  pipeline  的热拔插机制，即在AuthHandler内删除这个处理器
                         */
                        socketChannel.pipeline().addLast(new AuthHandler());
                        //创建聊天组
                        socketChannel.pipeline().addLast(new CreateGroupRequestHandler());
                        socketChannel.pipeline().addLast(new MessageRequestHandler());
                        //对处理的数据进行编码操作
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap,PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(System.currentTimeMillis() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
