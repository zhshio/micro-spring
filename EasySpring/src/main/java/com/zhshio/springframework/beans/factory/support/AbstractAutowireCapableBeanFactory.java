package com.zhshio.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 抽象的自动装配 Bean 工厂，继承自 AbstractBeanFactory。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 实例化策略，默认使用 Cglib 子类化实例化策略。
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建一个 bean 实例，并进行属性注入和初始化。
     *
     * @param beanName        bean 的名称
     * @param beanDefinition  bean 的定义
     * @param args            构造函数参数
     * @return 创建好的 bean 实例
     * @throws BeansException 如果实例化 bean 失败，抛出异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValue(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("实例化 bean 失败", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建 bean 实例。
     *
     * @param beanDefinition  bean 的定义
     * @param beanName        bean 的名称
     * @param args            构造函数参数
     * @return 创建好的 bean 实例
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
     * 对 bean 进行属性注入。
     *
     * @param beanName        bean 的名称
     * @param bean            bean 实例
     * @param beanDefinition  bean 的定义
     * @throws BeansException 如果属性注入失败，抛出异常
     */
    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("设置属性值出错：" + beanName);
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
}
