package com.zhshio.springframework.context;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * @description: 刷新容器
     * @author: 张帅
     * @date: 2024/1/3 20:12
     * @param: []
     * @return: []
     **/
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
