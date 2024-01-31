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

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
