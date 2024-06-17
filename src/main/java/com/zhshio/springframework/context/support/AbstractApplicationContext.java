package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;
import com.zhshio.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.zhshio.springframework.context.ApplicationEvent;
import com.zhshio.springframework.context.ApplicationListener;
import com.zhshio.springframework.context.ConfigurableApplicationContext;
import com.zhshio.springframework.context.event.ApplicationEventMulticaster;
import com.zhshio.springframework.context.event.ContextClosedEvent;
import com.zhshio.springframework.context.event.ContextRefreshedEvent;
import com.zhshio.springframework.context.event.SimpleApplicationEventMulticaster;
import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:30
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
/**
 * 抽象应用程序上下文类，扩展自DefaultResourceLoader并实现ConfigurableApplicationContext接口。
 * 该类提供了应用程序上下文的核心刷新逻辑，以及与事件广播和Bean后处理相关的功能。
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    // 应用事件多播器的bean名称。
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    // 应用事件多播器实例。
    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 刷新应用程序上下文，包括加载Bean定义、实例化Bean、注册Bean后处理器和初始化事件多播器等。
     * @throws BeansException 如果刷新过程中出现错误。
     */
    @Override
    public void refresh() throws BeansException {
        // 创建或刷新BeanFactory。
        refreshBeanFactory();
        // 获取BeanFactory实例。
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 注册ApplicationContextAware处理器。
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 处理BeanFactoryPostProcessors。
        invokeBeanFactoryPostProcessors(beanFactory);
        // 注册BeanPostProcessors。
        registerBeanPostProcessors(beanFactory);
        // 初始化应用事件多播器。
        initApplicationEventMulticaster();
        // 注册应用监听器。
        registerListeners();

        // 完成BeanFactory的初始化，包括实例化所有单例Bean。
        finishBeanFactoryInitialization(beanFactory);
        // 发布ContextRefreshedEvent事件。
        finishRefresh();
    }

    /**
     * 抽象方法，用于创建或刷新BeanFactory。
     * @throws BeansException 如果操作失败。
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 抽象方法，用于获取BeanFactory实例。
     * @return ConfigurableListableBeanFactory 当前上下文的BeanFactory。
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 处理所有的BeanFactoryPostProcessors。
     * @param beanFactory 当前的BeanFactory。
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册所有的BeanPostProcessors。
     * @param beanFactory 当前的BeanFactory。
     */
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 初始化应用事件多播器。
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册所有的应用监听器。
     */
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 完成BeanFactory的初始化，包括设置类型转换服务和实例化所有单例Bean。
     * @param beanFactory 当前的BeanFactory。
     */
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 设置类型转换器。
        if (beanFactory.containsBean("conversionService")) {
            Object conversionService = beanFactory.getBean("conversionService");
            if (conversionService instanceof ConversionService) {
                beanFactory.setConversionService((ConversionService) conversionService);
            }
        }
        // 实例化所有单例Bean。
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 在JVM关闭时注册关闭钩子以关闭应用程序上下文。
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 完成刷新过程，发布ContextRefreshedEvent事件。
     */
    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * 发布指定的事件。
     * @param event 要发布的事件。
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 根据类型获取所有匹配的Bean实例。
     * @param type Bean的类型。
     * @param <T> Bean的泛型类型。
     * @return 匹配的Bean实例映射。
     * @throws BeansException 如果操作失败。
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 获取所有Bean定义的名称。
     * @return 所有Bean定义的名称数组。
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 根据名称获取Bean实例。
     * @param name Bean的名称。
     * @return Bean的实例。
     * @throws BeansException 如果操作失败。
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    /**
     * 根据名称和参数获取Bean实例。
     * @param name Bean的名称。
     * @param args Bean实例化时的参数。
     * @return Bean的实例。
     * @throws BeansException 如果操作失败。
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 根据名称和类型获取Bean实例。
     * @param name Bean的名称。
     * @param requiredType Bean的期望类型。
     * @param <T> Bean的泛型类型。
     * @return Bean的实例。
     * @throws BeansException 如果操作失败。
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    /**
     * 根据类型获取Bean实例。
     * @param requiredType Bean的期望类型。
     * @param <T> Bean的泛型类型。
     * @return Bean的实例。
     * @throws BeansException 如果操作失败。
     */
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    /**
     * 检查是否包含指定名称的Bean。
     * @param name Bean的名称。
     * @return 如果包含返回true，否则返回false。
     */
    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    /**
     * 关闭应用程序上下文，包括发布ContextClosedEvent事件和销毁单例Bean。
     */
    @Override
    public void close() {
        // 发布ContextClosedEvent事件。
        publishEvent(new ContextClosedEvent(this));
        // 销毁所有单例Bean。
        getBeanFactory().destroySingletons();
    }

}
