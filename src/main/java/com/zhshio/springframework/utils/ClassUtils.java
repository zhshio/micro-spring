package com.zhshio.springframework.utils;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:20
 * @Description: com.zhshio.springframework.utils
 * @version: 1.0
 */
/**
 * 类工具类，提供关于类加载器和类的实用方法。
 */
public class ClassUtils {

    /**
     * 获取当前线程的默认类加载器。
     * 如果当前线程的上下文类加载器不可用，则使用本类的类加载器。
     *
     * @return 当前线程的类加载器，如果无法获取则返回null。
     */
    public static ClassLoader getDefaultClassLoader() {
        // 尝试获取当前线程的上下文类加载器
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 忽略获取上下文类加载器时可能发生的异常
        }

        // 如果当前线程的上下文类加载器为空，使用本类的类加载器作为备选
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }

        // 返回获取到的类加载器
        return cl;
    }

    /**
     * 判断给定的类是否是由CGLIB生成的代理类。
     *
     * @param clazz 待检查的类
     * @return 如果clazz是CGLIB代理类则返回true，否则返回false。
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 通过类名判断该类是否是由CGLIB生成的代理类。
     * CGLIB生成的代理类名称中通常包含"$$"。
     *
     * @param className 类的全限定名
     * @return 如果className是CGLIB代理类的名称则返回true，否则返回false。
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }

}
