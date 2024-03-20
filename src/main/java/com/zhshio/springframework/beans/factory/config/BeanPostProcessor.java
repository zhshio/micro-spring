package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:28
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
/**
 * Bean后处理器接口，提供在Spring Bean实例化过程中的自定义处理能力。
 * 可以在Bean实例化后的不同阶段插入自定义逻辑。
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象的初始化方法之前调用此方法。
     *
     * @param bean 将要被初始化的Bean实例。
     * @param beanName Bean的名称。
     * @return 修改后的Bean实例，如果返回null，则使用原始Bean实例。
     * @throws BeansException 如果处理中发生错误。
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后调用此方法。
     *
     * @param bean 已完成初始化的Bean实例。
     * @param beanName Bean的名称。
     * @return 修改后的Bean实例，如果返回null，则使用原始Bean实例。
     * @throws BeansException 如果处理中发生错误。
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}

