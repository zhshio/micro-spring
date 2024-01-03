package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:28
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
public interface BeanPostProcessor {


    /**
     * @description: 在 Bean对象初始化方法之前, 执行此方法
     * @author: 张帅
     * @date: 2024/1/3 20:08
     * @param: [bean, beanName]
     * @return: [java.lang.Object, java.lang.String]
     **/
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;


    /**
     * @description: 在Bean对象执行初始化方法之后, 执行此方法
     * @author: 张帅
     * @date: 2024/1/3 20:09
     * @param: [bean, beanName]
     * @return: [java.lang.Object, java.lang.String]
     **/
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
