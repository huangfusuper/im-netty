package com.im.protocol.base;

import lombok.Data;


/**
 * 通信过程中 Java 对象的抽象类，可以看到，
 * 我们定义了一个版本号（默认值为 1 ）以及一个获取指令的抽象方法，
 * 所有的指令数据包都必须实现这个方法，这样我们就可以知道某种指令的含义。
 * @author huangfu
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}