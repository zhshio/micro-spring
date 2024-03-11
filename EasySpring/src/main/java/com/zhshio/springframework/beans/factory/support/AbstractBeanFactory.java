package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.FactoryBean;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;
import com.zhshio.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.zhshio.springframework.context.ConfigurableApplicationContext;
import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.utils.ClassUtils;
import com.zhshio.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:56
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */

/**
 * 抽象的Bean工厂类，继承自FactoryBeanRegistrySupport并实现了ConfigurableBeanFactory接口。
 * 这个类主要用于创建和管理Bean实例提供了一系列方法来获取Bean实例、判断是否包含指定名称的Bean定义等。
 * 其中，核心方法是doGetBean，它通过调用getSingleton、getBeanDefinition和createBean等方法来真正获取Bean实例。
 * 此外，该类还提供了添加Bean后处理器、内嵌值解析器以及设置转换服务等功能。
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    // 使用默认的类加载器
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    // Bean后处理器列表
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    // 内嵌值解析器列表
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    // 转换服务
    private ConversionService conversionService;

    /**
     * 根据名称获取Bean实例。
     *
     * @param name Bean的名称。
     * @return 返回指定名称的Bean实例。
     * @throws BeansException 如果获取Bean时发生错误。
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据名称和参数获取Bean实例。
     *
     * @param name Bean的名称。
     * @param args 传给Bean构造函数的参数。
     * @return 返回指定名称的Bean实例。
     * @throws BeansException 如果获取Bean时发生错误。
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据名称和所需类型获取Bean实例。
     *
     * @param name Bean的名称。
     * @param requiredType 需要的Bean类型。
     * @return 返回指定名称和类型的Bean实例。
     * @throws BeansException 如果获取Bean时发生错误。
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 判断是否包含指定名称的Bean。
     *
     * @param name Bean的名称。
     * @return 如果包含则返回true，否则返回false。
     */
    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    /**
     * 判断是否包含指定名称的Bean定义。
     *
     * @param beanName Bean的名称。
     * @return 如果包含则返回true，否则返回false。
     */
    protected abstract boolean containsBeanDefinition(String beanName);

    /**
     * 真正获取Bean实例的方法。
     *
     * @param name Bean的名称。
     * @param args 传给Bean构造函数的参数。
     * @return 返回指定名称的Bean实例。
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是FactoryBean，则需要调用FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 根据bean实例获取对象。如果实例是FactoryBean，则会调用FactoryBean的相关方法获取对象。
     *
     * @param beanInstance Bean实例。
     * @param beanName Bean的名称。
     * @return 返回从bean实例中获取的对象。
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     * 获取指定Bean名称的Bean定义。
     *
     * @param beanName Bean的名称。
     * @return 返回Bean的定义。
     * @throws BeansException 如果获取Bean定义时发生错误。
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建指定名称和定义的Bean实例。
     *
     * @param beanName Bean的名称。
     * @param beanDefinition Bean的定义。
     * @param args 传给Bean构造函数的参数。
     * @return 返回创建的Bean实例。
     * @throws BeansException 如果创建Bean实例时发生错误。
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    /**
     * 添加一个Bean后处理器。
     *
     * @param beanPostProcessor Bean后处理器。
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 添加一个内嵌值解析器。
     *
     * @param valueResolver 内嵌值解析器。
     */
    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    /**
     * 解析内嵌的值。
     *
     * @param value 需要解析的值。
     * @return 返回解析后的值。
     */
    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * 设置转换服务。
     *
     * @param conversionService 转换服务。
     */
    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * 获取转换服务。
     *
     * @return 返回转换服务。
     */
    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * 获取Bean后处理器列表。
     *
     * @return 返回Bean后处理器列表。
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 获取Bean的类加载器。
     *
     * @return 返回Bean的类加载器。
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

}
