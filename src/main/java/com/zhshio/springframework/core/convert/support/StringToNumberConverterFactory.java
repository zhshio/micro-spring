package com.zhshio.springframework.core.convert.support;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:32
 * @Description: com.zhshio.springframework.core.convert.support
 * @version: 1.0
 */


import com.sun.istack.internal.Nullable;
import com.zhshio.springframework.core.convert.converter.Converter;
import com.zhshio.springframework.core.convert.converter.ConverterFactory;
import com.zhshio.springframework.utils.NumberUtils;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:32
 */

/**
 * 实现了将字符串转换为数字的ConverterFactory。
 * 这个类的作用是提供一个工厂方法，用于根据目标类型动态创建字符串到数字的转换器。
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    /**
     * 根据目标类型获取相应的转换器。
     *
     * @param targetType 转换器的目标类型，是一个Number的子类。
     * @return 返回一个能够将字符串转换为目标类型Number的转换器。
     */
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    /**
     * 字符串到数字的转换器实现类。
     * 这个类实现了将字符串转换为特定类型数字的功能。
     *
     * @param <T> 转换的目标类型，是Number的子类。
     */
    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        /**
         * 构造函数初始化目标类型。
         *
         * @param targetType 转换器的目标类型。
         */
        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        /**
         * 将字符串转换为目标类型的数字。
         *
         * @param source 待转换的字符串。
         * @return 转换后的数字。如果字符串为空，则返回null。
         */
        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
