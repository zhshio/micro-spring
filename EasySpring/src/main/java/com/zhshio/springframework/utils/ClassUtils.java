package com.zhshio.springframework.utils;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:20
 * @Description: com.zhshio.springframework.utils
 * @version: 1.0
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {

        }
        if (cl == null) {

            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
