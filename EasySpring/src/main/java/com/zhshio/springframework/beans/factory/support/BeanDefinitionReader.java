package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.core.io.ResourceLoader;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 21:41
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * Bean定义读取器接口，用于加载和注册Bean定义。
 */
public interface BeanDefinitionReader {

    /**
     * 获取Bean定义注册表。
     * @return BeanDefinitionRegistry 实例，用于存储和管理Bean定义。
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器。
     * @return ResourceLoader 实例，用于加载资源。
     */
    ResourceLoader getResourceLoader();

    /**
     * 从给定的资源加载Bean定义。
     * @param resource 要加载的资源，单个资源。
     * @throws BeansException 如果加载过程中发生错误。
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 从给定的资源数组加载Bean定义。
     * @param resources 要加载的资源数组。
     * @throws BeansException 如果加载过程中发生错误。
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 从给定的位置加载Bean定义。
     * @param location 资源的位置，单个位置。
     * @throws BeansException 如果加载过程中发生错误。
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 从给定的位置数组加载Bean定义。
     * @param locations 资源的位置数组。
     * @throws BeansException 如果加载过程中发生错误。
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
