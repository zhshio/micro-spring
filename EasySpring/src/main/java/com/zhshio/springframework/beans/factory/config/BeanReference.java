package com.zhshio.springframework.beans.factory.config;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:16
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
/**
 * 一个用于引用Bean的类。
 * 该类封装了对Spring Bean的引用，通过beanName来标识具体的Bean。
 */
public class BeanReference {
    // Bean的名称
    private final String beanName;

    /**
     * 构造函数，创建一个BeanReference实例。
     *
     * @param beanName 要引用的Bean的名称。这是一个不可变的字符串，一旦设置不能更改。
     */
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取被引用Bean的名称。
     *
     * @return 返回这个BeanReference实例所引用的Bean的名称。
     */
    public String getBeanName() {
        return beanName;
    }
}

