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

/**
 * 实现BeanPostProcessor接口，用于在Spring Bean的初始化前后进行额外的处理。
 * 本类的目的是为了自动将ApplicationContext注入到实现了ApplicationContextAware接口的Bean中。
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    /**
     * 保存ApplicationContext的实例。
     */
    private final ApplicationContext applicationContext;

    /**
     * 构造函数，初始化applicationContext。
     *
     * @param applicationContext Spring应用程序上下文的实例。
     */
    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 在Bean初始化之前进行处理。
     * 如果当前处理的Bean实现了ApplicationContextAware接口，则自动为其设置ApplicationContext。
     *
     * @param bean 待处理的Bean实例。
     * @param beanName Bean的名称。
     * @return 处理后的Bean实例。
     * @throws BeansException 如果处理过程中出现异常。
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if (bean instanceof ApplicationContextAware) {
           ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
       }

       return bean;
    }

    /**
     * 在Bean初始化之后进行处理。
     * 本方法中没有进行任何处理，主要为了实现BeanPostProcessor接口的完整性。
     *
     * @param bean 待处理的Bean实例。
     * @param beanName Bean的名称。
     * @return 处理后的Bean实例。
     * @throws BeansException 如果处理过程中出现异常。
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
