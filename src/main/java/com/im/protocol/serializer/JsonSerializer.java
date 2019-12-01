package com.im.protocol.serializer;

import com.alibaba.fastjson.JSON;
import com.im.protocol.enums.SerializerAlgorithm;
import com.im.protocol.serializer.base.IMSerializer;

/**
 * JSON方式的序列化方式
 * @author huangfu
 */
public class JsonSerializer implements IMSerializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON.getSerializerAlgorithm();
    }
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
