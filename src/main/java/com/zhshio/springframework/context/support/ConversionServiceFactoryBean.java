package com.zhshio.springframework.context.support;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:42
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */

import com.sun.istack.internal.Nullable;
import com.zhshio.springframework.beans.factory.FactoryBean;
import com.zhshio.springframework.beans.factory.InitializingBean;
import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.core.convert.converter.Converter;
import com.zhshio.springframework.core.convert.converter.ConverterFactory;
import com.zhshio.springframework.core.convert.converter.ConverterRegistry;
import com.zhshio.springframework.core.convert.converter.GenericConverter;
import com.zhshio.springframework.core.convert.support.DefaultConversionService;
import com.zhshio.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:42
 */

/**
 * 转换服务工厂Bean，用于创建ConversionService实例。
 * 实现了FactoryBean接口以提供ConversionService实例，
 * 并实现了InitializingBean接口以便在设置属性后初始化该实例。
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    /**
     * 待注册的转换器集合。
     * 可以为null，表示无需注册额外的转换器。
     */
    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    /**
     * 获取由该工厂Bean管理的ConversionService实例。
     *
     * @return ConversionService实例
     * @throws Exception 如果实例化时发生异常
     */
    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    /**
     * 返回由该FactoryBean创建的对象的类型。
     *
     * @return 创建对象的类型
     */
    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    /**
     * 指示由该FactoryBean返回的对象是否为单例。
     *
     * @return 总是返回true，表示返回的是单例对象
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * 属性设置完成后进行的初始化操作。
     * 初始化ConversionService实例，并注册转换器。
     *
     * @throws Exception 如果初始化过程中发生异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    /**
     * 注册给定集合中的转换器到转换器注册表中。
     *
     * @param converters 待注册的转换器集合
     * @param registry   转换器注册表
     */
    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("每个转换器对象必须实现Converter、ConverterFactory或GenericConverter接口之一");
                }
            }
        }
    }

    /**
     * 设置待注册的转换器集合。
     *
     * @param converters 转换器集合
     */
    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
