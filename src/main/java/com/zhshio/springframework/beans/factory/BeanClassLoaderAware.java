package com.zhshio.springframework.beans.factory;

/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:08
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * 该接口使Bean能够意识到类加载器，是Aware接口的一个扩展。
 * 它提供了一个方法来设置Bean的类加载器。
 */
public interface BeanClassLoaderAware extends Aware{

    /**
     * 设置Bean的类加载器。
     * @param classLoader 要设置的类加载器。
     */
    void setBeanClassLoader(ClassLoader classLoader);

}
