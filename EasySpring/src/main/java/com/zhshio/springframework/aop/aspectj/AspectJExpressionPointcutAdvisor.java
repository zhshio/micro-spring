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
/**
 * AspectJ表达式切面顾问类，实现了PointCutAdvisor接口。
 * 用于定义一个切面，该切面通过AspectJ表达式来确定要拦截的JoinPoint。
 */
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

    // 切点，使用AspectJ表达式来定义要拦截的点
    private AspectJExpressionPointcut pointcut;
    // 通知，即拦截到JoinPoint后要执行的具体逻辑
    private Advice advice;
    // 用于定义切点的AspectJ表达式
    private String expression;

    /**
     * 获取切面的通知。
     * @return 返回定义的拦截逻辑的具体通知对象。
     */
    @Override
    public Advice getAdvice() {
        return advice;
    }

    /**
     * 获取切点。
     * 如果内部的pointcut对象为null，则会根据expression创建一个新的AspectJExpressionPointcut对象。
     * @return 返回定义的切点对象。
     */
    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    /**
     * 设置通知。
     * @param advice 要设置的具体通知对象。
     */
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    /**
     * 设置表达式。
     * 该表达式用于定义切点。
     * @param expression 一个AspectJ表达式字符串。
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
}

