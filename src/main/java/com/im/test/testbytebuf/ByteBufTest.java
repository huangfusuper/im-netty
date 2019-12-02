package com.im.test.testbytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author huangfu
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("分配初始空间，读写之和为9，最大读写总和为100", buffer);
        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
        buffer.writeBytes(new byte[]{1,2,3,4});
        print("向ByteBuf中写入4个字节",buffer);
        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
        buffer.writeInt(12);
        print("写入一个int类型的值，一个int占四个字节",buffer);
        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
        buffer.writeBytes(new byte[]{5});
        print("再往里面写一个字节，此时分配的可写内存就满了，再写就该扩容了",buffer);
        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
        buffer.writeBytes(new byte[]{6});
        print("开始再往里面写，扩容了要",buffer);
        // get 方法不改变读写指针
        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        print("getByte()", buffer);

        // set 方法不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        print("setByte()", buffer);

        // read 方法改变读指针
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        print("readBytes(" + bytes.length + ")", buffer);

    }

    private static void print(String action,ByteBuf byteBuf){
        System.out.println("after=============="+action+"===============");
        System.out.println("ByteBuf底层内存的总容量:"+byteBuf.capacity());
        System.out.println("最大读写内存之和:"+byteBuf.maxCapacity());
        System.out.println("读指针所在位置:"+byteBuf.readerIndex());
        System.out.println("ByteBuf 当前可读的字节数，它的值等于 writerIndex-readerIndex:"+byteBuf.readableBytes());
        System.out.println("是否可读？:"+byteBuf.isReadable());
        System.out.println("写指针所在位置"+byteBuf.writerIndex());
        System.out.println("ByteBuf 当前可写的字节数，它的值等于 capacity-writerIndex:"+byteBuf.writableBytes());
        System.out.println("是否可写？:"+byteBuf.isWritable());
        System.out.println("表示可写的最大字节数，它的值等于 maxCapacity-writerIndex:"+byteBuf.maxWritableBytes());
        System.out.println();
    }
}
