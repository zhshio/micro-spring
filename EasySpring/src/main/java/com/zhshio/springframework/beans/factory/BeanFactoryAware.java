package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:07
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
