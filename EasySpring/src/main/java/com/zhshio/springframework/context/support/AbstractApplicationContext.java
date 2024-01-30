package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;
import com.zhshio.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.zhshio.springframework.context.ConfigurableApplicationContext;
import com.zhshio.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:30
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory, 并加载BeanDefinition
        refreshBeanFactory();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 在 Bean 实例化之前, 执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // BeanPostProcessors 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        // 提前实例化单例 Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    public void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
