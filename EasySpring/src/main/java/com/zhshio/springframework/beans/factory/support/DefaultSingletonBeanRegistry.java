package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.SingletonBeanRegistry;
import com.zhshio.springframework.beans.factory.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:58
 * @Description: 单例模板方法抽象类
 * @version: 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {

        return singletonObjects.get(beanName);
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
