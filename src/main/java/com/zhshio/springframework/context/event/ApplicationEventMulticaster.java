package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:37
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

import com.zhshio.springframework.context.ApplicationEvent;
import com.zhshio.springframework.context.ApplicationListener;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:37
 */

/**
 * 应用程序事件多播接口。
 * 该接口定义了如何向多个监听器广播应用程序事件。
 * 应用程序事件是应用程序中发生的任何感兴趣的事物，例如：用户登录、错误发生等。
 * 监听器通过实现ApplicationListener接口来订阅这些事件。
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加一个应用程序事件监听器。
     * 监听器将被调用以处理任何它感兴趣的事件。
     *
     * @param listener 要添加的事件监听器，它必须实现ApplicationListener接口。
     *                 听众对象是感兴趣的事件类型的具体实现。
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除一个应用程序事件监听器。
     * 移除后，该监听器将不再接收事件通知。
     *
     * @param listener 要移除的事件监听器，先前已通过addApplicationListener方法添加。
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 多播一个应用程序事件。
     * 该方法将事件发送给所有注册的监听器，每个监听器都会调用其onApplicationEvent方法。
     *
     * @param event 要多播的事件对象。事件对象应为ApplicationEvent的子类，包含事件的具体信息。
     */
    void multicastEvent(ApplicationEvent event);

}

