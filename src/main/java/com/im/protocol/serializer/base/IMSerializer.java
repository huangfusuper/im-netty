package com.im.protocol.serializer.base;

/**
 * 序列化算法
 * @author huangfu
 */
public interface IMSerializer {
    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */

    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     * @param clazz 转换的对象类型
     * @param bytes 需要转换的数字
     * @param <T> 返回的类型
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
