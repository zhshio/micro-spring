package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:34
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

import com.zhshio.springframework.context.ApplicationContext;
import com.zhshio.springframework.context.ApplicationEvent;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:34
 */

/**
 * 表示ApplicationContext事件的类。此类用于在应用程序上下文生命周期中的特定时刻触发事件。
 * 例如，当应用程序上下文被初始化或销毁时，可以使用此类来通知感兴趣的监听器。
 *
 * 继承自ApplicationEvent，ApplicationEvent是Spring框架中用于表示应用程序事件的基类。
 * 通过继承此类，ApplicationContextEvent可以利用ApplicationEvent的机制来发布和监听事件。
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * 构造函数，用于创建一个ApplicationContextEvent实例。
     *
     * @param source 事件的源对象，通常是触发事件的ApplicationContext。
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取触发此事件的应用程序上下文。
     *
     * 由于事件源是Object类型，此方法提供了类型转换的便利，直接返回ApplicationContext类型的对象。
     * 这样，调用者无需手动进行类型转换，可以直接使用返回的ApplicationContext。
     *
     * @return 触发此事件的应用程序上下文。
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
