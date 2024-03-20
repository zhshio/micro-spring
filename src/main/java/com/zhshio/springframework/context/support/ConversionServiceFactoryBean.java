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

public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

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
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}

