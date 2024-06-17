package com.zhshio.springframework.core.convert.converter;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:24
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:24
 */

/**
 * ConverterFactory接口定义了一个工厂，用于根据目标类型创建转换器。
 * 它的泛型参数S表示源类型，R表示目标类型通配符，允许任何继承自R的类型。
 * 该接口的主要作用是提供一个方法，通过目标类型的Class对象来获取特定于该类型的转换器。
 */
public interface ConverterFactory<S, R> {
    /**
     * 根据目标类型获取转换器。
     * 该方法使用泛型方法来确保返回的转换器适用于目标类型T，其中T是R的子类型。
     * 这种设计允许工厂动态地创建适用于不同目标类型的转换器，而无需为每个目标类型创建单独的工厂类。
     *
     * @param targetType 用于指定转换器应转换为目标的类型。这是目标类型的Class对象。
     * @param <T> 泛型参数，表示目标类型，它必须是R的子类型。
     * @return 返回一个转换器，该转换器可以将源类型S转换为目标类型T。
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}

