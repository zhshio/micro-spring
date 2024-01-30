package com.zhshio.springframework.test8.common;/**
 * @Auther: 张帅
 * @Date: 2024/1/3 - 01 - 03 - 22:37
 * @Description: com.zhshio.springframework.test.common
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/3 22:37
 */

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}

