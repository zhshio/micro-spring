package com.zhshio.springframework.beans.factory.support;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Auther: 张帅
 * @Date: 2023/11/15 - 11 - 15 - 15:39
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * Cglib子类化实例化策略类，实现了InstantiationStrategy接口。
 * 使用Cglib动态代理技术来创建对象的实例。
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    /**
     * 通过Cglib动态生成子类实例。
     *
     * @param beanDefinition Bean的定义信息，包含类信息等。
     * @param beanName Bean的名称。
     * @param ctor 要使用的构造函数，如果为null，则使用无参构造函数。
     * @param args 调用构造函数时传递的参数数组。
     * @return 实例化的对象。
     * @throws BeansException 如果实例化过程中发生错误。
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 使用Cglib的Enhancer创建一个代理对象配置器
        Enhancer enhancer = new Enhancer();
        // 设置代理对象的父类为beanDefinition中定义的类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置回调函数，这里使用了一个空操作的回调，仅为了示例
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 如果没有指定构造函数，则直接创建代理对象实例
        if (null == ctor) return enhancer.create();
        // 如果指定了构造函数，则使用指定的构造函数和参数创建代理对象实例
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}


