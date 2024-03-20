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

public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
