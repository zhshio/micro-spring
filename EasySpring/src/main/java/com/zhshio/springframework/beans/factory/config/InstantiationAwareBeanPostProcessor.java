package com.zhshio.springframework.beans.factory.config;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 23:13
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 23:13
 */

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
