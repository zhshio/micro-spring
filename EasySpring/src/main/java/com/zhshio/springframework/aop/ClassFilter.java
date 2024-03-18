package com.zhshio.springframework.aop;

/**
 * @Auther: 张帅
 * @Date: 2024/2/1 - 02 - 01 - 11:07
 * @Description: com.zhshio.springframework.aop
 * @version: 1.0
 */
/**
 * 类过滤器接口，用于定义匹配特定条件的类的逻辑。
 */
public interface ClassFilter {
    /**
     * 判断给定的类是否匹配特定条件。
     *
     * @param clazz 待匹配的类，这是一个泛型参数，可以是任何类型的类。
     * @return 如果给定的类匹配条件，则返回true；否则返回false。
     */
    boolean matches(Class<?> clazz);
}

