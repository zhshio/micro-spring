package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:26
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import org.aopalliance.aop.Advice;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 22:26
 */

/**
 * 代表一个在目标方法执行前执行的建议接口，继承自Advice接口。
 * 该接口没有定义具体的方法，其主要作用是通过实现该接口的类来提供在方法执行前的逻辑处理。
 */
public interface BeforeAdvice extends Advice {

}

