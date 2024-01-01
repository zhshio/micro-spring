package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:18
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;


    String[] getBeanDefinitionNames();

}
