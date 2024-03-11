package com.zhshio.springframework.beans.factory;/**
 * @Auther: 张帅
 * @Date: 2024/1/3 - 01 - 03 - 22:06
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */

/**
 * @description:
 * @author: zs
 * @time: 2024/1/3 22:06
 */

/**
 * InitializingBean接口定义了一个Bean属性填充完成后需要调用的方法。
 * 这个接口通常被用来在Bean实例化并且所有属性被设置完成后执行一些特殊的初始化逻辑。
 */
public interface InitializingBean {

    /**
     * 在Bean的所有属性被设置完成后调用的方法。
     * 该方法可以用来执行任何必要的初始化步骤，比如初始化资源、验证配置等。
     *
     * @throws Exception 如果在初始化过程中遇到任何问题，该方法可以抛出异常。
     */
    void afterPropertiesSet() throws Exception;
}
