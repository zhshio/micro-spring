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

public class ContextRefreshedEvent extends ApplicationContextEvent{
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}