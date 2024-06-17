package com.zhshio.springframework.core.convert.converter;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:26
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:26
 */

/**
 * 接口ConverterRegistry用于注册和管理转换器。
 * 它允许系统识别和使用不同的转换器来处理类型之间的转换。
 */
public interface ConverterRegistry {

    /**
     * 注册一个通用转换器。
     *
     * @param convert 要注册的转换器，它实现了Converter接口，用于特定类型的转换。
     *                添加这个转换器是为了支持从一种类型到另一种类型的转换。
     */
    void addConverter(Converter<?, ?> convert);

    /**
     * 注册一个泛型转换器。
     *
     * @param converter 要注册的泛型转换器，它实现了GenericConverter接口，
     *                  用于处理多种类型的转换。这种转换器更具灵活性，可以处理多种类型的转换需求。
     */
    void addConverter(GenericConverter converter);

    /**
     * 注册一个转换器工厂。
     *
     * @param converterFactory 要注册的转换器工厂，它实现了ConverterFactory接口。
     *                         转换器工厂用于根据需要动态创建转换器，这提供了一种更灵活的转换器注册和管理方式。
     *                         添加这个转换器工厂可以支持更复杂或动态的类型转换需求。
     */
    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

}

