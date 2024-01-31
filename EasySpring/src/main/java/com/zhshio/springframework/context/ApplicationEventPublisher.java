package com.zhshio.springframework.context;

/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 22:28
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
