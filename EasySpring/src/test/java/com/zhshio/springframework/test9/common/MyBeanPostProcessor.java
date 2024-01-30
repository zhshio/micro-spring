package com.zhshio.springframework.test9.common;/**
 * @Auther: 张帅
 * @Date: 2024/1/3 - 01 - 03 - 22:38
 * @Description: com.zhshio.springframework.test.common
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;
import com.zhshio.springframework.test9.bean.UserService;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/3 22:38
 */

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}

