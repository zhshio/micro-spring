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

// Bean 属性填充完成后调用
public interface InitializingBean {


    void afterPropertiesSet() throws Exception;
}
