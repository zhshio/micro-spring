package com.zhshio.springframework.context.annotation;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 12:08
 * @Description: com.zhshio.springframework.context.annotation
 * @version: 1.0
 */

import cn.hutool.core.util.StrUtil;
import com.zhshio.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.zhshio.springframework.context.stereotype.Component;

import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 12:08
 */

/**
 * 自动扫描指定包下符合条件的类，并将其注册为Spring的Bean Definition。
 * 此类扩展了ClassPathScanningCandidateComponentProvider，提供了更具体的扫描和注册逻辑。
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    // 注册表，用于注册扫描到的Bean Definition
    private BeanDefinitionRegistry registry;

    /**
     * 构造函数，初始化扫描器并指定Bean Definition的注册表。
     *
     * @param registry Bean Definition注册表，用于注册扫描到的Bean。
     */
    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 执行扫描指定包下的类，并注册为Bean Definition。
     *
     * @param basePackages 需要扫描的基包，多个包用字符串数组表示。
     */
    public void doScan(String... basePackages) {
        // 遍历每个基包，进行扫描
        for (String basePackage : basePackages) {
            // 找到候选的Bean Definition
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            // 遍历每个候选Bean Definition
            for (BeanDefinition beanDefinition : candidates) {
                // 解析Bean的作用域
                // 解析 Bean 作用域 singleton, prototype
                String beanScope = resolveBeanScope(beanDefinition);
                // 如果作用域不为空，则设置到Bean Definition上
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                // 确定Bean的名称并注册到注册表中
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
            // 注册AutowiredAnnotationBeanPostProcessor，用于处理@Autowired注解
            registry.registerBeanDefinition("com.zhshio.springframework.context.annotation.internalAutowiredAnnotationProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
        }
    }

    /**
     * 解析Bean的作用域。
     *
     * @param beanDefinition Bean Definition对象
     * @return Bean的作用域字符串，如果找不到则返回空字符串。
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取类上的Scope注解，如果存在则返回其value值
        Scpoe scope = beanClass.getAnnotation(Scpoe.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }

    /**
     * 确定Bean的名称。
     *
     * @param beanDefinition Bean Definition对象
     * @return Bean的名称字符串。
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取类上的Component注解，如果存在则返回其value值
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        // 如果Component注解的value为空，则使用类名的简单形式，并首字母小写作为Bean名称
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
