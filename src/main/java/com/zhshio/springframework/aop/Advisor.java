package com.zhshio.springframework.aop;
/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:28
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import org.aopalliance.aop.Advice;

/**
 * 顾问接口。定义了顾问的基本行为，顾问主要是提供具体的建议。
 */
public interface Advisor {

    /**
     * 获取建议的方法。
     *
     * @return 返回具体的建议对象，该对象由实现此接口的具体类定义。
     */
    Advice getAdvice();
}
