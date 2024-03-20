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

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
