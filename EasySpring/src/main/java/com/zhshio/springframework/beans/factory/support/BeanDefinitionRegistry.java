package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 20:06
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
