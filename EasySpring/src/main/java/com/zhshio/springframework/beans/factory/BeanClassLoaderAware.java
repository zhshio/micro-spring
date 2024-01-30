package com.zhshio.springframework.beans.factory;

/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:08
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}