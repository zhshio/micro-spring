package com.zhshio.springframework;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:03
 * @Description: com.zhshio.springframework
 * @version: 1.0
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
