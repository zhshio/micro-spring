package com.zhshio.springframework.context;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 11:40
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */

import java.util.EventListener;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 11:40
 */

/**
 * 应用事件监听器接口。
 * <p>
 * 该接口用于定义一个监听应用事件的监听器。实现此接口的类将能够接收和处理特定类型的ApplicationEvent事件。
 * 应用事件是应用中发生的特定事件，例如应用程序启动、停止，或者自定义业务事件。
 * 监听器通过实现onApplicationEvent方法来指定如何处理接收到的事件。
 *
 * @param <E> 泛型参数，表示监听的具体应用事件类型，必须继承自ApplicationEvent。
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 当监听到应用事件时调用的方法。
     * <p>
     * 此方法由Spring框架在检测到相应的应用事件发生时调用。监听器实现此方法以处理接收到的特定类型的应用事件。
     *
     * @param event 发生的应用事件，类型为泛型E，允许监听器根据事件类型执行特定的处理逻辑。
     */
    void onApplicationEvent(E event);

}

