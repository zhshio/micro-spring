package com.zhshio.springframework.context;/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:09
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.Aware;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/30 22:09
 */

/**
 * 实现这个接口的Bean可以自动获取ApplicationContext实例。
 * 该接口扩展了Aware接口，为Bean提供了一种无需依赖特定 ApplicationContext 实现的方式，
 * 来获取运行时的 ApplicationContext。
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置ApplicationContext。
     * 这个方法由Spring框架调用，用来注入 ApplicationContext 实例到实现这个接口的Bean中。
     * 通过这个方法，Bean可以在不直接依赖特定 ApplicationContext 实现的情况下，
     * 访问 ApplicationContext 中的其他Bean和资源。
     *
     * @param applicationContext ApplicationContext实例，提供了访问所有Bean和应用配置的能力。
     * @throws BeansException 如果设置ApplicationContext失败。
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}

