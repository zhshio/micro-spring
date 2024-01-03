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

public interface InitializingBean {

    /**
     * @description: Bean 处理了属性填充后调用
     * @author: 张帅
     * @date: 2024/1/3 22:07
     * @param: []
     * @return: []
     **/
    void afterPropertiesSet() throws Exception;
}
