package com.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @program: im-netty->ServerHandler
 * @description: 服务端处理器
 * @author: huangfu
 * @date: 2019/12/3 17:03
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(System.currentTimeMillis()+"客户端读到数据:"+byteBuf.toString(Charset.forName("utf-8")));
        //回复客户端
        System.out.println(System.currentTimeMillis()+"服务端写出数据:" );
        Thread.sleep(20);
        ctx.channel().writeAndFlush(getByteBuf(ctx));

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        String meg = "我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈我叫皇甫科星，我太帅了；帅的我自己都爱上了自己！我的天，我真妖孽！么么么么么，啊哈哈哈哈哈";
        byte[] bytes = meg.getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc( ).buffer( );
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
