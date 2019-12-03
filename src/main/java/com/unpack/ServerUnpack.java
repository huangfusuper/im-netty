package com.unpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: im-netty->ServerUnpack
 * @description: 拆包粘包处理
 * @author: huangfu
 * @date: 2019/12/3 16:59
 **/
public class ServerUnpack {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worked = new NioEventLoopGroup();

        serverBootstrap.group(boss,worked)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 64)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>( ) {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                });

        serverBootstrap.bind(8000);
    }
}
