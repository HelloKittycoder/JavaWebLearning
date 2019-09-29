package com.kittycoder.runtime;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created by shucheng on 2019-9-29 下午 16:07
 * Marshalling工厂
 */
public final class MarshallingCodeFactory {

    /**
     * 创建Jboss Marshalling解码器MarshallingDecoder
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder() {
        // 首先通过Marshalling工具类的方法获取Marshalling实例对象 参数serial标识创建的时java序列化工厂对象
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        // 创建MarshallingConfiguration脆响，配置版本号为5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        // 根据marshallerFactory和configuration创建provider
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        // 构建Netty的MarshallingDecoder对象，两个参数分别是provider和单个消息序列化后的最大长度
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
        return decoder;
    }

    /**
     * 创建Jboss Marshalling编码器MarshallingEncoder
     * @return
     */
    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        // 构建Netty的MarshallingEncoder对象，两个参数分别是provider和单个消息序列化后的最大长度
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }
}
