package com.zhshio.springframework.context;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:10
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */

import java.util.EventObject;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:10
 */
/**
 * 抽象类ApplicationEvent表示应用程序中发生的特定事件。
 * 它是EventObject的子类，为事件驱动的架构提供基础。
 * 此类的目的是封装应用程序中发生的事件，以便其他对象可以注册监听这些事件并作出响应。
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * 构造函数初始化ApplicationEvent对象。
     *
     * @param source 事件的来源。通常是触发事件的对象。
     *               事件源对象是事件的发起者，它不一定是事件的处理者。
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}

