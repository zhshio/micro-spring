package com.zhshio.springframework.beans.factory;

/**
 * @Auther: 张帅
 * @Date: 2024/1/3 - 01 - 03 - 21:56
 * @Description: com.zhshio.springframework.beans.factory.xml
 * @version: 1.0
 */

/**
 * DisposableBean接口定义了容器在销毁Bean时需要执行的操作。
 * 它提供了一个方法，让Bean可以在其生命周期结束时进行一些必要的清理工作。
 */
public interface DisposableBean {

    /**
     * 销毁Bean实例的方法。当容器准备销毁一个Bean时，会调用此方法。
     * 这个方法允许Bean执行任何必要的清理动作，比如关闭资源等。
     *
     * @throws Exception 如果在销毁Bean时发生错误，则抛出Exception。
     */
    void destroy() throws Exception;
}
