package com.zhshio.springframework.utils;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:20
 * @Description: com.zhshio.springframework.utils
 * @version: 1.0
 */
public class ClassUtils {

    /**
     * @description: 获取当前线程的默认类加载器，如果当前线程上下文类加载器不可用，则返回调用该方法的类的类加载器。
     * @author: 张帅
     * @date: 2024/01/01 22:30
     * @param: []
     * @return: java.lang.ClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        // 尝试获取当前线程的上下文类加载器
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 忽略异常并继续尝试获取其他类加载器
        }

        // 如果当前线程的上下文类加载器为null，则使用该 ClassUtils 类自身的类加载器
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }

        // 返回最终获取到的类加载器（可能为null）
        return cl;
    }
}