package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:32
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 22:32
 */

public interface PointCutAdvisor extends Advisor{

    Pointcut getPointcut();
}
