package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:56
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (null != bean) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract  Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
