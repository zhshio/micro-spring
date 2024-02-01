package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:23
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:23
 */

public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
