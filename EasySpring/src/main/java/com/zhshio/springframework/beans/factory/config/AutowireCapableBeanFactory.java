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
     * @description: 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization
     * @author: 张帅
     * @date: 2024/1/3 21:25
     * @param: [existingBean, name]
     * @return: [java.lang.Object, java.lang.String]
     **/
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String name) throws BeansException;

    /**
     * @description: 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization
     * @author: 张帅
     * @date: 2024/1/3 21:26
     * @param: [existingBean, beanName]
     * @return: [java.lang.Object, java.lang.String]
     **/
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
