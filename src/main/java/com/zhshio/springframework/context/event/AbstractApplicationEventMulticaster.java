package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:43
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.BeanFactoryAware;
import com.zhshio.springframework.context.ApplicationEvent;
import com.zhshio.springframework.context.ApplicationListener;
import com.zhshio.springframework.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:43
 */

/**
 * 抽象的应用程序事件多播器类，实现了应用程序事件的多播功能。
 * 该类为抽象类，提供了添加、移除应用事件监听器的功能，并定义了事件的多播逻辑。
 * 同时，它实现了BeanFactoryAware接口，以便于获取BeanFactory实例。
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    /**
     * 保存所有应用事件监听器的集合，使用LinkedHashSet以保证添加的顺序。
     */
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    /**
     * BeanFactory实例，用于获取事件监听器和其他依赖项。
     */
    private BeanFactory beanFactory;

    /**
     * 添加一个应用事件监听器。
     *
     * @param listener 要添加的监听器，必须实现ApplicationListener接口。
     */
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    /**
     * 移除一个应用事件监听器。
     *
     * @param listener 要移除的监听器。
     */
    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    /**
     * 设置BeanFactory实例。
     * 该方法实现了BeanFactoryAware接口的要求，用于获取BeanFactory。
     *
     * @param beanFactory BeanFactory实例。
     * @throws BeansException 如果设置失败。
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取适用于特定事件的应用事件监听器集合。
     *
     * @param event 发生的事件。
     * @return 适用于该事件的监听器集合。
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 判断给定的监听器是否支持特定的事件。
     * 该方法通过检查监听器的类型参数来确定它是否能处理特定类型的事件。
     *
     * @param applicationListener 要检查的监听器。
     * @param event               要检查的事件。
     * @return 如果监听器支持该事件，则返回true；否则返回false。
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 检查是否是CGLIB代理类，如果是，则获取其超类
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 获取监听器类型参数，以确定它能处理的事件类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 检查事件类是否能被监听器处理
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
