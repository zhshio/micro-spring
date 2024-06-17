package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:30
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
/**
 * 抽象类AbstractRefreshableApplicationContext继承自AbstractApplicationContext，提供了刷新bean工厂的能力。
 * 它定义了如何创建和加载bean定义，但具体的bean定义加载逻辑由子类实现。
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    // 用于存储bean实例的容器
    private DefaultListableBeanFactory beanFactory;

    /**
     * 刷新bean工厂。
     * 这个方法首先创建一个新的DefaultListableBeanFactory实例，然后加载bean定义，最后将这个bean工厂赋值给类级变量beanFactory。
     * @throws BeansException 如果在刷新过程中出现错误
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建一个新的DefaultListableBeanFactory实例。
     * @return 新的DefaultListableBeanFactory实例
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 子类需要实现这个方法来加载bean定义到提供的bean工厂中。
     * @param beanFactory 用于加载bean定义的bean工厂
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 获取bean工厂。
     * @return 当前应用上下文中的bean工厂
     */
    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
