package com.zhshio.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:40
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */

/**
 * 转换服务接口，定义了类型转换的能力。
 * 提供了检查是否支持特定转换以及执行转换的方法。
 */
public interface ConversionService {

    /**
     * 检查是否支持从源类型到目标类型的转换。
     *
     * @param sourceType 源对象的类，可能为null，表示源类型未知或任意类型。
     * @param targetType 目标类型的类。
     * @return 如果支持转换，则返回true；否则返回false。
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * 将源对象转换为目标类型。
     *
     * @param source 待转换的源对象。
     * @param targetType 要转换成的目标类型。
     * @param <T> 目标类型的泛型参数。
     * @return 转换后的目标对象。
     * @throws IllegalArgumentException 如果转换不支持或源对象无法转换为目标类型。
     */
    <T> T convert(Object source, Class<T> targetType);

}
