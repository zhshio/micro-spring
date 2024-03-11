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

/**
 * FactoryBean接口，提供了一种创建对象的抽象工厂模式。这个接口允许用户定义自己的工厂逻辑来创建对象，而不需要实现具体的工厂类。
 * 它是Spring框架中非常重要的一个接口，可以用来创建singleton对象、 prototype对象等。
 *
 * @param <T> 生成对象的类型
 */
public interface FactoryBean<T> {

    /**
     * 获取对象实例。这个方法是工厂的核心方法，用于创建并返回一个对象实例。
     *
     * @return 创建的对象实例。返回的对象类型取决于实现类。
     * @throws Exception 如果在创建对象时发生错误，可以抛出异常。
     */
    T getObject() throws Exception;

    /**
     * 获取对象的类型。这个方法用于返回工厂将要创建的对象的类型。
     *
     * @return 对象的类型，即工厂产品的Class对象。
     */
    Class<?> getObjectType();

    /**
     * 判断工厂生产的对象是否是单例。如果返回true，表示工厂每次调用getObject方法都会返回相同的对象实例；
     * 如果返回false，则表示每次调用getObject方法都会返回一个新的对象实例。
     *
     * @return true表示是单例，false表示不是单例。
     */
    boolean isSingleton();
}

