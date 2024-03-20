package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ObjectFactory;
import com.zhshio.springframework.beans.factory.config.SingletonBeanRegistry;
import com.zhshio.springframework.beans.factory.DisposableBean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:58
 * @Description: 单例模板方法抽象类
 * @version: 1.0
 */


public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 用于表示空对象的静态常量
    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存，存储已创建的单例对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，存储提前暴露的单例对象，这些对象可能还未完全初始化
    private Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    // 三级缓存，存储单例对象的工厂，用于按需创建单例对象
    private Map<String, ObjectFactory<?>>  singletonFactories = new HashMap<>();

    // 存储需要在销毁时执行特定销毁逻辑的单例bean
    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    /**
     * 获取指定名称的单例对象。
     * 首先从一级缓存中查找，若未找到则尝试从二级缓存中查找。
     * 若仍未找到，则从三级缓存中创建并存入二级缓存。
     *
     * @param beanName 要获取的单例对象的名称
     * @return 指定名称的单例对象
     */
    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    /**
     * 将单例对象的工厂添加到注册表中。
     * 如果一级缓存中已存在该对象，则不进行添加。
     *
     * @param beanName 要添加的单例对象的名称
     * @param singletonFactory 单例对象的工厂
     */
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    /**
     * 注册一个单例对象，将其添加到一级缓存中。
     * 同时移除该对象在二级缓存和三级缓存中的记录。
     *
     * @param beanName 要注册的单例对象的名称
     * @param singletonObject 要注册的单例对象
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    /**
     * 注册一个需要在销毁时执行销毁逻辑的bean。
     *
     * @param beanName 要注册的bean的名称
     * @param bean 需要注册的DisposableBean接口实现对象
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /**
     * 销毁所有注册的单例对象，调用其DisposableBean接口定义的destroy方法。
     */
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
