package com.zhshio.springframework.test11.event;


import com.zhshio.springframework.context.ApplicationListener;
import com.zhshio.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
