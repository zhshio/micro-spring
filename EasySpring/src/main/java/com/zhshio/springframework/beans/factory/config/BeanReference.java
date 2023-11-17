package com.zhshio.springframework.beans.factory.config;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:16
 * @Description: com.zhshio.springframework.beans.factory.config
 * @version: 1.0
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
