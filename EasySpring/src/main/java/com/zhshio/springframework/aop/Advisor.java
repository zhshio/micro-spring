package com.zhshio.springframework.aop;
/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:28
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import org.aopalliance.aop.Advice;

public interface Advisor {

    Advice getAdvice();
}
