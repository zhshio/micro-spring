package com.zhshio.springframework.beans.factory.annotation;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 21:37
 * @Description: com.zhshio.springframework.beans.factory.annotation
 * @version: 1.0
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.BeanFactoryAware;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.zhshio.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.utils.ClassUtils;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 21:37
 */

/**
 * 用于处理@Autowired和@Value注解的Bean后处理器。
 * 实现了InstantiationAwareBeanPostProcessor和BeanFactoryAware接口。
 */

public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;

    /**
     * 设置BeanFactory。
     *
     * @param beanFactory Bean工厂对象。
     * @throws BeansException 如果设置过程中发生错误。
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * 在Bean属性设置后处理属性值。
     *
     * @param pvs 属性值。
     * @param bean 目标Bean对象。
     * @param beanName Bean名称。
     * @return 处理后的属性值。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 处理@Value注解，进行值注入
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        // 遍历字段，处理@Value注解，进行值注入
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);

                // 进行类型转换
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if (conversionService != null) {
                    if (conversionService.canConvert(sourceType, targetType)) {
                        value = conversionService.convert(value, targetType);
                    }
                }

                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 处理@Autowired注解，进行Bean依赖注入
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }


    /**
     * 该类实现了BeanFactoryPostProcessor接口，用于在Spring容器初始化Bean之前和之后对Bean进行处理。
     */

        // 在Bean初始化之前不进行任何处理，直接返回null。
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return null;
        }

        // 在Bean初始化之后不进行任何处理，直接返回null。
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            return null;
        }

        // 在实例化Bean之前不进行任何处理，直接返回null。
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            return null;
        }

        // 在Bean实例化之后进行处理，始终返回true表示处理完成。
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            return true;
        }
}
