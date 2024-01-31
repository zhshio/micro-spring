package com.zhshio.springframework.context.event;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 22:34
 * @Description: com.zhshio.springframework.context.event
 * @version: 1.0
 */

import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.context.ApplicationEvent;
import com.zhshio.springframework.context.ApplicationListener;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 22:34
 */

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }


    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
