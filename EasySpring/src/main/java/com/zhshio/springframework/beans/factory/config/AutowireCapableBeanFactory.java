package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.BeanFactory;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:14
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 在Bean初始化之前应用BeanPostProcessors接口实现类的postProcessBeforeInitialization方法。
     *
     * @param existingBean 将要被初始化的Bean实例。
     * @param name Bean的名称。
     * @return 经过BeanPostProcessors处理后的Bean实例，可能是原始Bean或其代理。
     * @throws BeansException 如果处理过程中发生错误。
     **/
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String name) throws BeansException;

    /**
     * 在Bean初始化之后应用BeanPostProcessors接口实现类的postProcessAfterInitialization方法。
     *
     * @param existingBean 已完成初始化的Bean实例。
     * @param beanName Bean的名称。
     * @return 经过BeanPostProcessors处理后的Bean实例，可能是原始Bean或其代理。
     * @throws BeansException 如果处理过程中发生错误。
     **/
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
