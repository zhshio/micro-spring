package com.zhshio.springframework.context;/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 22:09
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.Aware;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/30 22:09
 */

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
