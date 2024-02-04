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

public interface ObjectFactory<T>{

    T getObject() throws BeansException;
}
