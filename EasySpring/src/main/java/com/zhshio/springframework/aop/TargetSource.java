package com.zhshio.springframework.aop;/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:23
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */

import com.zhshio.springframework.utils.ClassUtils;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 11:23
 */

/**
 * 代表一个目标对象的来源。
 */
public class TargetSource {

    // 目标对象
    private final Object target;

    /**
     * 构造函数。
     *
     * @param target 目标对象，不允许为null。
     */
    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取目标对象的类数组。如果目标对象是CGLIB代理，则返回代理对象的超类的接口数组；
     * 否则，返回目标对象的类的接口数组。
     *
     * @return 类数组，代表目标对象实现的接口。
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        // 检查是否为CGLIB代理，是则获取其超类，否则直接获取目标类
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 获取目标对象。
     *
     * @return 目标对象。
     */
    public Object getTarget() {
        return this.target;
    }
}
