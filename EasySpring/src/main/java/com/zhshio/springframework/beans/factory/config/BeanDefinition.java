package com.zhshio.springframework.beans.factory.config;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:03
 * @Description: com.zhshio.springframework
 * @version: 1.0
 */
public class BeanDefinition {

    //将初版Object改成Class, 将Bean的实例化延迟到容器中处理
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

}
