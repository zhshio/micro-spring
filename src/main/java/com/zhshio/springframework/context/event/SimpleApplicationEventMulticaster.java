package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 22:34
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.context.ApplicationEvent;
import com.zhshio.springframework.context.ApplicationListener;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 22:34
 */
/**
 * 简单的应用程序事件多播器类，继承自AbstractApplicationEventMulticaster。
 * 该类负责向多个监听器广播应用程序事件。
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    /**
     * 构造函数，初始化SimpleApplicationEventMulticaster实例。
     *
     * @param beanFactory 用于获取应用程序监听器的BeanFactory。
     *                    通过BeanFactory，可以获取到所有注册的监听器，以便广播事件。
     */
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * 多播给定的事件到所有的监听器。
     * 该方法遍历所有监听器，并调用它们的onApplicationEvent方法，以广播事件。
     *
     * @param event 要多播的应用程序事件。
     *              该事件将被传递到所有的监听器，以便它们可以相应地处理。
     */
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        // 遍历所有监听该类型事件的监听器
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            // 调用监听器的onApplicationEvent方法，广播事件
            listener.onApplicationEvent(event);
        }
    }

}
