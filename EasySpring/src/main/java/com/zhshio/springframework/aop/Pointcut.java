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

/**
 * 切面接口，定义了切面的类过滤器和方法匹配器。
 */
public interface Pointcut {

    /**
     * 获取类过滤器。
     * @return 返回一个类过滤器，用于确定哪些类被此切面关注。
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配器。
     * @return 返回一个方法匹配器，用于确定哪些方法被此切面关注。
     */
    MethodMatcher getMethodMatcher();
}

