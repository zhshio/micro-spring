package com.zhshio.springframework.aop.aspectj;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:16
 * @Description: com.zhshio.springframework.aop.aspectj
 * @version: 1.0
 */

import com.zhshio.springframework.aop.ClassFilter;
import com.zhshio.springframework.aop.MethodMatcher;
import com.zhshio.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:16
 */

/**
 * 一个实现Pointcut接口的类，用于通过AspectJ表达式定义切点。
 * 它同时实现了ClassFilter和MethodMatcher接口，以便于匹配类和方法。
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    // 支持的切点原语集合
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        // 初始化支持的切点原语集合，这里只支持EXECUTION原语
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    // 存储解析后的切点表达式
    private final PointcutExpression pointcutExpression;

    /**
     * 构造函数，用于创建一个AspectJ表达式切点。
     *
     * @param expression 表示切点的AspectJ表达式字符串。
     */
    public AspectJExpressionPointcut(String expression) {
        // 使用支持的切点原语和当前类的类加载器解析切点表达式
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 判断给定的类是否匹配切点表达式。
     *
     * @param clazz 要检查的类。
     * @return 如果类可能包含与切点表达式匹配的连接点，则返回true；否则返回false。
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 判断给定的方法和类是否匹配切点表达式。
     *
     * @param method 要检查的方法。
     * @param targetClass 方法所属的类。
     * @return 如果方法执行与切点表达式完全匹配，则返回true；否则返回false。
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 返回此对象作为ClassFilter的实例。
     *
     * @return 作为ClassFilter的this引用。
     */
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * 返回此对象作为MethodMatcher的实例。
     *
     * @return 作为MethodMatcher的this引用。
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

}
