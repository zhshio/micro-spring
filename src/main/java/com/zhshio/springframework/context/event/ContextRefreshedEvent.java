package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:36
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:36
 */

/**
 * 当应用程序上下文被刷新时触发的事件。
 * <p>
 * 这个类继承自ApplicationContextEvent，用于表示应用程序上下文被成功初始化或刷新的事件。
 * 通常，这会在ApplicationContext的refresh方法被调用并完成所有bean的实例化、配置和初始化后触发。
 * 可以通过监听这个事件来执行一些上下文初始化后的操作，比如注册定时任务、初始化静态资源等。
 *
 * @author [Your Name]
 * @see ApplicationContextEvent
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * 构造函数，用于创建一个ContextRefreshedEvent实例。
     *
     * @param source 事件的来源，通常是触发事件的ApplicationContext实例。
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
