package com.zhshio.springframework.context;

/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 22:28
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */

/**
 * 接口ApplicationEventPublisher用于发布应用事件。
 * 它提供了一个方法来通知所有感兴趣的监听器，一个应用事件已经发生。
 */
public interface ApplicationEventPublisher {

    /**
     * 发布给定的事件，这将导致所有注册的监听器调用它们的onApplicationEvent方法。
     *
     * @param event 要发布的事件对象，它应该是一个ApplicationEvent的实例。
     *              事件对象包含了事件发生的时间、源对象等信息。
     */
    void publishEvent(ApplicationEvent event);

}
