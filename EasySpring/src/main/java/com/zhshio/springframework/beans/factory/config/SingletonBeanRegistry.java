package com.zhshio.springframework.beans.factory.config;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:55
 * @Description: 获取单例对象的接口
 * @version: 1.0
 */

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
