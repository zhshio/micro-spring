package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:06
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:06
 */

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
