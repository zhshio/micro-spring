package com.zhshio.springframework.beans.factory;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 9:24
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 9:24
 */

public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
