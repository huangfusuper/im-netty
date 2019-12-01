package com.im.protocol.enums;

import com.im.protocol.base.Packet;
import com.im.protocol.serializer.JsonSerializer;
import com.im.protocol.serializer.base.IMSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 序列化算法标识
 * @author huangfu
 */

public enum SerializerAlgorithm {
    JSON(Byte.parseByte("1"),new JsonSerializer(),"FastJson序列化方式")
    ;
    private byte serializerAlgorithm;
    private IMSerializer imSerializer;
    private String serializerMsg;

    SerializerAlgorithm(byte serializerAlgorithm, IMSerializer imSerializer, String serializerMsg) {
        this.serializerAlgorithm = serializerAlgorithm;
        this.imSerializer = imSerializer;
        this.serializerMsg = serializerMsg;
    }

    public byte getSerializerAlgorithm() {
        return serializerAlgorithm;
    }

    public IMSerializer getImSerializer() {
        return imSerializer;
    }

    public String getSerializerMsg() {
        return serializerMsg;
    }

    public static IMSerializer match(byte code){

        List<SerializerAlgorithm> collect = Arrays.asList(SerializerAlgorithm.values()).stream().
                filter(serializerAlgorithm -> serializerAlgorithm.serializerAlgorithm == code).
                collect(Collectors.toList());
        if(collect != null && collect.size()>0){
            return collect.get(0).getImSerializer();
        }
        return null;
    }
}
