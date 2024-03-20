package com.zhshio.springframework.beans.factory.config;

import com.sun.istack.internal.Nullable;
import com.zhshio.springframework.beans.factory.HierarchicalBeanFactory;
import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.utils.StringValueResolver;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:13
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
/**
 * 提供配置bean的能力的接口，扩展了HierarchicalBeanFactory和SingletonBeanRegistry接口。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    // 单例作用域标识符
    String SCOPE_SINGLETON = "singleton";

    // 原型作用域标识符
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加一个Bean后处理器。
     * @param beanPostProcessor 要添加的Bean后处理器实例。
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁所有单例bean。
     */
    void destroySingletons();

    /**
     * 添加一个嵌入值解析器。
     * @param valueResolver 要添加的字符串值解析器。
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解析一个嵌入值。
     * @param value 要解析的值。
     * @return 解析后的值。
     */
    String resolveEmbeddedValue(String value);

    /**
     * 设置转换服务。
     * @param conversionService 要设置的转换服务实例。
     */
    void setConversionService(ConversionService conversionService);

    /**
     * 获取转换服务。
     * @return 转换服务实例，如果未设置则返回null。
     */
    @Nullable
    ConversionService getConversionService();


}
