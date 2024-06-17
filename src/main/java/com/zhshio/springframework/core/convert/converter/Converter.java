package com.zhshio.springframework.core.convert.converter;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:23
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:23
 */

/**
 * Converter接口定义了一个从一种类型到另一种类型转换的规范。
 * 它包含一个方法convert，用于实现具体的转换逻辑。
 *
 * @param <S> 源类型的参数化类型，表示要转换的原始对象的类型。
 * @param <T> 目标类型的参数化类型，表示转换后的对象的类型。
 */
public interface Converter<S, T> {

    /**
     * 将指定的源对象转换为目标类型。
     *
     * @param source 源对象，需要进行类型转换。
     * @return 转换后的目标类型对象。
     * @throws 无法转换时，可能抛出异常。
     */
    T convert(S source);
}
