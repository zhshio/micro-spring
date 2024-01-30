package com.zhshio.springframework.beans.factory;

/**
 * @Auther: 张帅
 * @Date: 2024/1/3 - 01 - 03 - 21:56
 * @Description: com.zhshio.springframework.beans.factory.xml
 * @version: 1.0
 */

// 容器销毁 Bean的时候获得一次回调
public interface DisposableBean {

    void destroy() throws Exception;
}
