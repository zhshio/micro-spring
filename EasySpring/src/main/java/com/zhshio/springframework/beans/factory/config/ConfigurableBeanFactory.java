package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.factory.HierarchicalBeanFactory;
import com.zhshio.springframework.utils.StringValueResolver;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:13
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";



    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
