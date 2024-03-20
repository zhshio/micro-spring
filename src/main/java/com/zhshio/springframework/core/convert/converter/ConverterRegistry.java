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

public interface ConverterRegistry {

    void addConverter(Converter<?, ?> convert);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

}
