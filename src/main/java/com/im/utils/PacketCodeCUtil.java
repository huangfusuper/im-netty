package com.im.utils;

import com.im.protocol.packet.LoginRequestPacket;
import com.im.protocol.base.Packet;
import com.im.protocol.enums.Command;
import com.im.protocol.enums.SerializerAlgorithm;
import com.im.protocol.serializer.JsonSerializer;
import com.im.protocol.serializer.base.IMSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 编解码工具类
 * @author huangfu
 */
public class PacketCodeCUtil {
    /**
     * 定义魔数 表示自定义算法
     */
    private static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 编码
     * @param packet
     * @param imSerializer
     * @return
     */
    public static ByteBuf encode(Packet packet, IMSerializer imSerializer){
        //1.创建ByteBuf对象 分配器 定义一个适合IO的缓冲区
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        //2.序列化JAVA对象
        byte[] serializeObjBuf = imSerializer.serialize(packet);
        // 3. 实际编码过程
        //写入魔数  按照规定4字节
        byteBuf.writeInt(MAGIC_NUMBER);
        //写入版本号  按照规定 一字节
        byteBuf.writeByte(packet.getVersion());
        //写入序列化算法  按照规定一字节
        byteBuf.writeByte(imSerializer.getSerializerAlgorithm());
        //写入指令 按照规定1字节
        byteBuf.writeByte(packet.getCommand());
        //写入字节长度，按照规定四字节
        byteBuf.writeInt(serializeObjBuf.length);
        //写入最终数据
        byteBuf.writeBytes(serializeObjBuf);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf,IMSerializer imSerializer){
        //跳过魔数 后续校验
        byteBuf.skipBytes(4);
        //跳过 版本号
        byteBuf.skipBytes(1);

        //获取编解码算法
        byte codecIdentification = byteBuf.readByte();
        //获取指令
        byte command  = byteBuf.readByte();
        //获取数据包长度
        int dataLength = byteBuf.readInt();
        //数据字节
        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);

        //获取命令编码
        Class<? extends Packet> packet = Command.match(command);
        IMSerializer serializer = SerializerAlgorithm.match(codecIdentification);
        if (packet != null && serializer != null) {
            return imSerializer.deserialize(packet,bytes);
        }
        return null;
    }
}


class Test1{
    public static void main(String[] args) {
        ByteBuf encode = PacketCodeCUtil.encode(new LoginRequestPacket("123","123213","123"), new JsonSerializer());
        Packet decode = PacketCodeCUtil.decode(encode, new JsonSerializer());
        System.out.println(decode);
    }
}
