package com.zhshio.springframework.aop.aspectj;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 22:34
 * @Description: com.zhshio.springframework.aop.aspectj
 * @version: 1.0
 */

import com.zhshio.springframework.aop.Advisor;
import com.zhshio.springframework.aop.PointCutAdvisor;
import com.zhshio.springframework.aop.Pointcut;
import org.aopalliance.aop.Advice;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 22:34
 */

public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体拦截方法
    private Advice advice;
    // 表达式
    private String expression;



    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
