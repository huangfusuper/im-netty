package com.lifecycle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: im-netty->LifecycleServer
 * @description: 生命周期查看的服务
 * @author: huangfu
 * @date: 2019/12/17 9:57
 **/
public class LifecycleServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worked = new NioEventLoopGroup();

        /**
         * ChannelOption.SO_BACKLOG,1024:
         *      BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
         *          用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
         * ChannelOption.SO_KEEPALIVE,true:
         *       是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）
         *          并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
         * ChannelOption.TCP_NODELAY, true:
         *       在TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
         *        TCP_NODELAY就是用于启用或关于Nagle算法。如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
         */
        serverBootstrap.group(boss,worked).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,64)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>( ) {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("逻辑处理器被添加：handlerAdded()");
                                super.handlerAdded(ctx);
                            }

                            @Override
                            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channel 绑定到线程(NioEventLoop)：channelRegistered()");
                                super.channelRegistered(ctx);
                            }

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channel 准备就绪：channelActive()");
                                super.channelActive(ctx);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("channel 有数据可读：channelRead()");
                                super.channelRead(ctx, msg);
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channel 某次数据读完：channelReadComplete()");
                                super.channelReadComplete(ctx);
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channel 被关闭：channelInactive()");
                                super.channelInactive(ctx);
                            }

                            @Override
                            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
                                super.channelUnregistered(ctx);
                            }

                            @Override
                            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("逻辑处理器被移除：handlerRemoved()");
                                super.handlerRemoved(ctx);
                            }
                        });
                    }
                });
    }
}
