package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.core.io.DefaultResourceLoader;
import com.zhshio.springframework.core.io.ResourceLoader;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 21:51
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}

