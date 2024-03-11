package com.zhshio.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.*;
import com.zhshio.springframework.beans.factory.config.*;
import com.zhshio.springframework.core.convert.ConversionService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Wrapper;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:56
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * 抽象类，用于提供Bean的实例化和管理Bean的生命周期。
 * 继承自AbstractBeanFactory，实现了AutowireCapableBeanFactory接口。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    // 实例化策略，默认使用CglibSubclassingInstantiationStrategy
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建一个Bean实例前，解析Bean定义并可能返回一个代理对象。
     * 如果返回了代理对象，则直接使用该代理对象，否则继续实例化Bean。
     *
     * @param beanName Bean的名称。
     * @param beanDefinition Bean的定义信息。
     * @param args 用于实例化Bean的参数。
     * @return 实例化的Bean或代理对象。
     * @throws BeansException 如果在实例化过程中发生错误。
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        // 尝试在实例化前解析并可能返回一个代理对象
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean) {
            return bean; // 如果解析出代理对象，则直接返回该对象
        }

        // 如果没有解析出代理对象，则继续实例化Bean
        return doCreateBean(beanName, beanDefinition, args);
    }


    /**
     * 真正创建Bean实例的方法。
     *
     * @param beanName Bean名称
     * @param beanDefinition Bean定义
     * @param args 构造函数参数
     * @return 创建的Bean实例
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
//        try {
            // 实例化Bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 处理循环依赖，提前暴露单例Bean
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }

            // 在设置属性之前判断是否继续填充属性
            boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation) {
                return bean;
            }
            // 允许BeanPostProcessor在设置属性之前修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 设置Bean属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 初始化Bean及调用BeanPostProcessor的前后处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
//        } catch (Exception e) {
//            throw new BeansException("Instantiation of bean failed", e);
//        }

        // 注册实现了DisposableBean接口的Bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 根据Bean的scope注册或获取Bean的实例
        Object exposedObject = bean;
        if (beanDefinition.isSingleton()) {
            exposedObject = getSingleton(beanName);
        }
        return exposedObject;

    }


    /**
     * 创建Bean实例。
     *
     * @param beanDefinition Bean定义
     * @param beanName Bean名称
     * @param args 构造函数参数
     * @return 实例化的Bean对象
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }



    /**
     * 应用属性值到Bean实例上。
     *
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     * @throws BeansException 如果设置属性值时发生错误
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 解析依赖的Bean实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                } else {
                    // 进行类型转换
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService != null) {
                        if (conversionService.canConvert(sourceType, targetType)) {
                            value = conversionService.convert(value, targetType);
                        }
                    }
                }

                // 设置属性值
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName + "message: " + e);
        }
    }

    /**
     * 初始化Bean实例。
     *
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     * @return 初始化后的Bean实例
     * @throws BeansException 如果初始化过程中发生错误
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        // 调用aware方法，注入beanFactory等
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 执行BeanPostProcessor的before初始化处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 执行Bean的初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 执行BeanPostProcessor的after初始化处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 调用Bean的初始化方法。
     *
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     * @throws Exception 如果调用初始化方法失败
     */
    private void invokeInitMethods(String beanName, Object bean,  BeanDefinition beanDefinition) throws Exception {
        // 调用实现InitializingBean接口的afterPropertiesSet方法
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 调用注解配置的init-method方法
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }


    /**
     * 应用BeanPostProcessor的before初始化处理。
     *
     * @param existingBean 存在的Bean实例
     * @param beanName Bean名称
     * @return 处理后的Bean实例
     * @throws BeansException 如果应用处理时发生错误
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    /**
     * 应用BeanPostProcessor的after初始化处理。
     *
     * @param existingBean 存在的Bean实例
     * @param beanName Bean名称
     * @return 处理后的Bean实例
     * @throws BeansException 如果应用处理时发生错误
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    /**
     * 注册需要销毁的Bean。
     *
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 只有Singleton类型的Bean才会执行销毁方法
        if (!beanDefinition.isSingleton()) return;

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }


    /**
     * 获取实例化策略。
     *
     * @return 实例化策略
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略。
     *
     * @param instantiationStrategy 实例化策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 在应用属性之前应用BeanPostProcessor。
     *
     * @param beanName Bean名称
     * @param bean Bean实例
     * @param beanDefinition Bean定义
     */
    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    /**
     * 在实例化Bean之前解析并可能返回一个代理对象。
     * 此方法首先尝试应用Bean后处理器（BeanPostProcessor）来在Bean实例化之前处理Bean类，
     * 如果应用了后处理器且返回了非null的对象，则进一步对这个对象应用Bean后处理器的初始化后处理。
     *
     * @param beanName Bean的名称。
     * @param beanDefinition Bean的定义，包含了如何实例化Bean的信息。
     * @return 可能的代理对象。如果没有任何后处理器产生代理对象，则返回null。
     */
    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        // 应用Bean实例化前的后处理器，并尝试返回处理后的Bean实例
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        // 如果后处理器返回了非null的Bean实例，则进一步应用初始化后的Bean后处理器处理
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }


    /**
     * 获取早期bean引用。
     * 在bean实例化之前，通过BeanPostProcessor接口的实现获取bean的早期引用。如果实现了InstantiationAwareBeanPostProcessor接口的BeanPostProcessor存在，
     * 则会调用其getEarlyBeanReference方法，可能提前返回一个bean的引用。
     *
     * @param beanName bean的名称
     * @param beanDefinition bean的定义
     * @param bean 实际的bean实例
     * @return 早期bean引用或bean本身
     */
    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) {
        Object exposedObject = bean;
        // 遍历所有BeanPostProcessor，查找InstantiationAwareBeanPostProcessor并获取早期bean引用
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                // 如果获取的引用为null，则直接返回
                if (null == exposedObject) return exposedObject;
            }
        }

        return exposedObject;
    }

    /**
     * 在bean实例化之前应用BeanPostProcessor。
     * 遍历所有BeanPostProcessor，如果存在实现InstantiationAwareBeanPostProcessor接口的处理器，
     * 则调用其postProcessBeforeInstantiation方法，可能会提前返回一个bean实例。
     *
     * @param beanClass 要实例化的bean的类
     * @param beanName bean的名称
     * @return 经过处理的bean实例，如果为null，则继续正常实例化流程
     * @throws BeansException 如果处理中发生错误
     */
    public Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor)processor).postProcessBeforeInstantiation(beanClass, beanName);
                // 如果处理器返回非null对象，则提前返回这个对象
                if (null != result) return result;
            }
        }
        return null;
    }

    /**
     * 在bean实例化之后应用BeanPostProcessor。
     * 检查是否所有实现InstantiationAwareBeanPostProcessor接口的BeanPostProcessor都通过了postProcessAfterInstantiation检查。
     * 如果所有处理器都通过，则继续属性填充；如果任一处理器失败，则停止属性填充。
     *
     * @param beanName bean的名称
     * @param bean bean实例
     * @return 如果所有处理器通过，则返回true，否则返回false
     */
    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        // 遍历所有BeanPostProcessor，应用InstantiationAwareBeanPostProcessor的postProcessAfterInstantiation
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                // 如果处理器返回false，则停止属性填充
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }


}