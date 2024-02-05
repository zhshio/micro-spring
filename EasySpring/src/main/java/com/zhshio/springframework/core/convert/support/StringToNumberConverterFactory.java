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

public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

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

