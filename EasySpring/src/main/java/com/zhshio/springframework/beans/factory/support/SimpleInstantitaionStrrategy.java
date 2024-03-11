package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/15 - 11 - 15 - 15:26
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * 简单实例化策略类，实现了InstantiationStrategy接口。
 * 用于根据BeanDefinition定义实例化一个对象。
 */
public class SimpleInstantitaionStrrategy implements InstantiationStrategy{
    /**
     * 使用指定的构造器参数实例化一个对象。
     *
     * @param beanDefinition 定义了要实例化的bean的信息。
     * @param beanName bean的名称。
     * @param ctor 要使用的构造器，如果为null，则使用无参构造器。
     * @param args 要传递给构造器的参数数组。
     * @return 实例化的对象。
     * @throws BeansException 如果实例化过程中发生错误。
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass(); // 获取bean的类对象
        try {
            if(null != ctor) {
                // 如果指定了构造器，则使用指定的构造器实例化对象
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                // 如果没有指定构造器，则使用无参构造器实例化对象
                return clazz.getDeclaredConstructor().newInstance();
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // 如果在实例化过程中发生异常，则抛出BeansException
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
