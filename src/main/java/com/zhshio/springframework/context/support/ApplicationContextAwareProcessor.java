package com.zhshio.springframework.context.support;/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:10
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;
import com.zhshio.springframework.context.ApplicationContext;
import com.zhshio.springframework.context.ApplicationContextAware;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/30 22:10
 */

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if (bean instanceof ApplicationContextAware) {
           ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
       }

       return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
