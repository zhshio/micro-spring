package com.zhshio.springframework.beans.factory.config;

import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:03
 * @Description: com.zhshio.springframework
 * @version: 1.0
 */
/**
 * 定义Bean的配置信息，包括Bean的类、属性值、作用域、初始化方法和销毁方法等。
 */
public class BeanDefinition {

    // 单例作用域常量
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    // 原型作用域常量
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    // Bean的类
    private Class beanClass;

    // Bean的属性值
    private PropertyValues propertyValues;

    // Bean的初始化方法名
    private String initMethodName;

    // Bean的销毁方法名
    private String destroyMethodName;

    // Bean的作用域，默认为单例
    private String scope = SCOPE_SINGLETON;

    // 标记是否为单例
    private boolean singleton = true;

    // 标记是否为原型
    private boolean prototype = false;

    /**
     * 构造函数，初始化一个BeanDefinition实例，设置Bean的类并创建空的属性值对象。
     *
     * @param beanClass Bean的类
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    /**
     * 构造函数，初始化一个BeanDefinition实例，设置Bean的类和属性值。
     *
     * @param beanClass Bean的类
     * @param propertyValues Bean的属性值
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    /**
     * 设置Bean的作用域。
     *
     * @param scope Bean的作用域
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    /**
     * 获取Bean的作用域。
     *
     * @return Bean的作用域
     */
    public String getScope() {
        return scope;
    }

    /**
     * 判断Bean是否为单例。
     *
     * @return 如果Bean为单例，则返回true，否则返回false
     */
    public boolean isSingleton() {
        return singleton;
    }

    /**
     * 判断Bean是否为原型。
     *
     * @return 如果Bean为原型，则返回true，否则返回false
     */
    public boolean isPrototype() {
        return prototype;
    }

    /**
     * 获取Bean的类。
     *
     * @return Bean的类
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置Bean的属性值。
     *
     * @param propertyValues Bean的属性值
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    /**
     * 获取Bean的属性值。
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 获取Bean的初始化方法名。
     *
     * @return Bean的初始化方法名
     */
    public String getInitMethodName() {
        return initMethodName;
    }

    /**
     * 设置Bean的初始化方法名。
     *
     * @param initMethodName Bean的初始化方法名
     */
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    /**
     * 获取Bean的销毁方法名。
     *
     * @return Bean的销毁方法名
     */
    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    /**
     * 设置Bean的销毁方法名。
     *
     * @param destroyMethodName Bean的销毁方法名
     */
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

}
