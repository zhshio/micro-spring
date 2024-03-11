package com.zhshio.springframework.beans.factory;

/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:08
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * BeanNameAware接口，继承自Aware接口。提供了一个方法用于设置Bean的名称。
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置Bean的名称。
     *
     * @param name 要设置的Bean名称，类型为String。
     */
    void setBeanName(String name);

}
