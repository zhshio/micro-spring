package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:28
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
