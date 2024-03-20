package com.zhshio.springframework.beans.factory;/**
 * @Auther: 张帅
 * @Date: 2024/2/4 - 02 - 04 - 14:38
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/4 14:38
 */

/**
 * 这是一个泛型接口ObjectFactory，用于创建并提供某种类型的对象。
 * @param <T> 该接口所创建对象的类型。
 */
public interface ObjectFactory<T>{

    /**
     * 获取一个对象实例。
     * @return 返回一个T类型的对象实例。如果无法创建对象，将抛出BeansException异常。
     * @throws BeansException 如果在获取对象实例时发生错误，则抛出此异常。
     */
    T getObject() throws BeansException;
}
