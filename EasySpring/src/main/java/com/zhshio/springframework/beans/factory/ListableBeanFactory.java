package com.zhshio.springframework.beans.factory;

import com.zhshio.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:18
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * 提供一种接口，以便在应用程序上下文中访问一组特定类型的bean定义。
 * 这个接口扩展了BeanFactory接口，提供了更高级别的bean访问功能。
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 获取指定类型的所有bean实例。
     *
     * @param type 指定的bean类型。
     * @return 一个包含所有匹配bean实例的Map，键为bean的名字，值为bean的实例。
     * @throws BeansException 如果在获取bean时发生错误。
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回当前上下文中所有bean定义的名字。
     *
     * @return 包含所有bean定义名字的字符串数组。
     */
    String[] getBeanDefinitionNames();
}

