package com.zhshio.springframework.core.convert.support;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:28
 * @Description: com.zhshio.springframework.core.convert.support
 * @version: 1.0
 */

import com.zhshio.springframework.core.convert.converter.ConverterRegistry;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:28
 */

public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}

