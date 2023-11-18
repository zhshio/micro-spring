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
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
