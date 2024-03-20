package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.core.io.DefaultResourceLoader;
import com.zhshio.springframework.core.io.ResourceLoader;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 21:51
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * 抽象类AbstractBeanDefinitionReader，用于加载和注册Bean定义。
 * 实现了BeanDefinitionReader接口，提供了一种灵活的方式来加载资源并注册Bean定义到给定的BeanDefinitionRegistry中。
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    // Bean定义注册表，用于注册和管理Bean定义。
    private final BeanDefinitionRegistry registry;

    // 资源加载器，用于加载资源。
    private ResourceLoader resourceLoader;

    /**
     * 构造函数，初始化一个Bean定义注册表和使用默认资源加载器。
     *
     * @param registry Bean定义注册表，用于注册Bean定义。
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 构造函数，初始化一个Bean定义注册表和指定的资源加载器。
     *
     * @param registry Bean定义注册表，用于注册Bean定义。
     * @param resourceLoader 资源加载器，用于加载Bean定义资源。
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取Bean定义注册表。
     *
     * @return 返回当前使用的Bean定义注册表。
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取资源加载器。
     *
     * @return 返回当前使用的资源加载器。
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
