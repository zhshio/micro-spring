package com.zhshio.springframework.aop.framework.autoproxy;

import com.zhshio.springframework.aop.*;
import com.zhshio.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.zhshio.springframework.aop.framework.ProxyFactory;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.BeanFactoryAware;
import com.zhshio.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zhshio.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/1 23:11
 */

/**
 * 默认的顾问自动代理创建器，用于在Spring Bean初始化之前和之后创建代理。
 * 实现了InstantiationAwareBeanPostProcessor和BeanFactoryAware接口。
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    // 用于存储提前引用的代理对象的集合，保证线程安全
    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<Object>());

    /**
     * 设置BeanFactory。
     *
     * @param beanFactory BeanFactory对象。
     * @throws BeansException 如果设置失败。
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 在实例化Bean之前进行处理，本方法不进行处理直接返回null。
     *
     * @param beanClass 要实例化的Bean的类。
     * @param beanName Bean的名称。
     * @return null，表示不干预实例化过程。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 在实例化Bean之后进行处理，总是返回true表示成功。
     *
     * @param bean 实例化的Bean对象。
     * @param beanName Bean的名称。
     * @return true，表示处理成功。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    /**
     * 判断给定的类是否为基础设施类（如Advice, Pointcut, Advisor）。
     *
     * @param beanClass 要判断的类。
     * @return 如果是基础设施类，则返回true，否则返回false。
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    /**
     * 在Bean初始化之前进行处理，直接返回Bean对象。
     *
     * @param bean 初始化前的Bean对象。
     * @param beanName Bean的名称。
     * @return Bean对象，未进行代理处理。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在Bean初始化之后进行处理，如果该Bean未被提前代理，则进行代理。
     *
     * @param bean 初始化后的Bean对象。
     * @param beanName Bean的名称。
     * @return 如果Bean已被代理，则返回代理对象；否则返回原Bean对象。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }

        return bean;
    }

    /**
     * 获取提前引用的Bean的代理对象，并将其加入到提前引用的代理集合中。
     *
     * @param bean 未代理的Bean对象。
     * @param beanName Bean的名称。
     * @return 代理对象。
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    /**
     * 如果必要，对给定的Bean创建并返回一个代理对象。如果Bean是基础设施类，则不进行代理。
     *
     * @param bean 要代理的Bean对象。
     * @param beanName Bean的名称。
     * @return 如果创建了代理，则返回代理对象；否则返回原Bean对象。
     */
    protected Object wrapIfNecessary(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())) return bean;

        // 获取所有AspectJExpressionPointcutAdvisor类型的Bean
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 判断当前Bean是否匹配切点表达式
            if (!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            // 设置目标对象来源
            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            // 设置方法拦截器
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            // 设置方法匹配器
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            // 指定使用CGLIB代理
            advisedSupport.setProxyTargetClass(true);

            // 创建并返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }

        return bean;
    }

    /**
     * 对Bean的属性值进行后处理，本方法不进行处理直接返回原属性值。
     *
     * @param pvs Bean的属性值。
     * @param bean Bean对象。
     * @param beanName Bean的名称。
     * @return 原属性值。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }


}
