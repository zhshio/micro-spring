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

/**
 * 默认转换服务类，继承自GenericConversionService。
 * 该类旨在提供一套预定义的转换器，以支持常见的类型转换。
 */
public class DefaultConversionService extends GenericConversionService{

    /**
     * 默认构造函数。
     * 调用addDefaultConverters方法来初始化转换器注册表，确保系统启动时就具备基本的类型转换能力。
     */
    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    /**
     * 静态方法，用于向给定的转换器注册表中添加默认的转换器工厂。
     * 这些转换器工厂负责提供一系列从字符串到数值类型的转换器，是系统运行所必需的。
     *
     * @param converterRegistry 转换器注册表，用于存储和管理转换器的实例。
     */
    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }

}


