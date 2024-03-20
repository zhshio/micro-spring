package com.zhshio.springframework.beans.factory.config;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 23:13
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValues;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 23:13
 */

/**
 * 提供在 bean 实例化前后以及属性设置过程中进行定制处理的扩展点，继承自 BeanPostProcessor 接口。
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在实例化 bean 之前调用，允许修改 bean 类或提供代理。
     *
     * @param beanClass 待实例化的 bean 类。
     * @param beanName bean 的名称。
     * @return 如果修改了 bean 类，则返回修改后的 bean 类的实例；否则返回 null。
     * @throws BeansException 如果处理中发生错误。
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 bean 实例化之后调用，判断是否继续进行后续的处理。
     *
     * @param bean 实例化的 bean 对象。
     * @param beanName bean 的名称。
     * @return 如果返回 true，则继续处理；如果返回 false，则停止后续处理。
     * @throws BeansException 如果处理中发生错误。
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 在设置 bean 属性之前调用，允许修改属性值。
     *
     * @param pvs bean 的属性值。
     * @param bean 实例化的 bean 对象。
     * @param beanName bean 的名称。
     * @return 修改后的属性值。
     * @throws BeansException 如果处理中发生错误。
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 提供提前访问 bean 的引用，允许返回代理而不是原始 bean 实例。
     * 默认实现直接返回传入的 bean 实例。
     *
     * @param bean 原始 bean 实例。
     * @param beanName bean 的名称。
     * @return 早期的 bean 引用，可能是原始 bean 或代理。
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}

