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

public interface ConverterFactory<S, R> {
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
