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

/**
 * 切面顾问接口，继承自Advisor接口。切面顾问是指定了切点和通知（advice）的组合，它定义了拦截哪些方法执行的规则。
 *
 * @author <your name>
 * @since <version>
 */
public interface PointCutAdvisor extends Advisor{

    /**
     * 获取切点对象。
     *
     * @return 返回定义了哪些方法应被拦截的切点对象。
     */
    Pointcut getPointcut();
}

