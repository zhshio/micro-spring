package com.zhshio.springframework.context;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;
}
