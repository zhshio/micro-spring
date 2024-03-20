package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:07
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * 该接口提供了一个方法，用于将BeanFactory注入到实现了此接口的Bean中。
 * 是Aware接口系列的一部分，提供对BeanFactory的访问能力。
 */
public interface BeanFactoryAware extends Aware{

    /**
     * 设置BeanFactory。
     * 该方法用于将BeanFactory实例注入到当前Bean中，以便当前Bean可以访问Spring上下文中的其他Bean。
     *
     * @param beanFactory 要设置的BeanFactory实例。
     * @throws BeansException 如果在设置BeanFactory时发生错误。
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
