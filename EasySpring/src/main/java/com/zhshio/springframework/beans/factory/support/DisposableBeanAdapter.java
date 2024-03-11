package com.zhshio.springframework.beans.factory.support;/**
 * @Auther: 张帅
 * @Date: 2024/1/30 - 01 - 30 - 21:26
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */

import cn.hutool.core.util.StrUtil;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.DisposableBean;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/30 21:26
 */

/**
 * 一个适配器类，用于适配并管理一个可销毁对象的销毁过程。
 * 实现了DisposableBean接口。
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean; // 被管理的bean对象
    private final String beanName; // bean的名称

    private String destroyMethodName; // 销毁方法的名称


    /**
     * 构造函数，初始化被管理的bean、bean名称和bean定义。
     *
     * @param bean 被管理的bean对象。
     * @param beanName bean的名称。
     * @param beanDefinition bean的定义信息，用于获取销毁方法名。
     */
    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    /**
     * 销毁bean。首先检查bean是否实现了DisposableBean接口，如果实现了就调用其destroy方法。
     * 接着，如果配置了销毁方法名（非空），且该方法不是DisposableBean的destroy方法，则尝试调用配置的销毁方法。
     *
     * @throws Exception 如果销毁过程中发生错误。
     */
    @Override
    public void destroy() throws Exception {
        // 如果bean实现了DisposableBean接口，则调用其destroy方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 调用配置的销毁方法（如果存在且未被重复调用）
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
