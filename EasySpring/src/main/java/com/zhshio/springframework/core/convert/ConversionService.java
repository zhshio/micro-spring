package com.zhshio.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:40
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */
public interface ConversionService {

    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);

}
