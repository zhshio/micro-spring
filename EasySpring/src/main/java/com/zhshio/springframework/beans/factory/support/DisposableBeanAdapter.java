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

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;

    private String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 配置信息 destroy-method (判断是为了避免二次执行销毁)
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
