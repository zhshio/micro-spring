package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:35
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:35
 */

/**
 * 当应用程序上下文关闭时触发的事件。
 * <p>
 * 该类继承自{@link ApplicationContextEvent}，用于表示应用程序上下文关闭的事件。
 * 当Spring应用程序上下文被关闭时，此事件会被发布，允许监听器响应上下文的关闭事件。
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * 构造函数，用于创建一个表示上下文关闭事件的新实例。
     *
     * @param source 事件的源对象，通常是触发关闭事件的应用程序上下文。
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}

