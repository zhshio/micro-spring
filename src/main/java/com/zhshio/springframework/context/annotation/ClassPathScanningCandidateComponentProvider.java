package com.zhshio.springframework.context.annotation;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 11:51
 * @Description: com.zhshio.springframework.context.annotation
 * @version: 1.0
 */

import cn.hutool.core.util.ClassUtil;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.context.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 11:51
 */

/**
 * 类路径扫描候选组件提供者。
 * 该类用于在指定的类路径下扫描带有特定注解的类，以确定Spring Bean的定义。
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 在指定的包中查找候选组件。
     * 候选组件是指被Spring的@Component注解标记的类，这些类可以被注册为Spring容器中的Bean。
     *
     * @param basePackage 扫描的包名。这个包及其子包下的类将被扫描。
     * @return 返回一个Set集合，包含所有找到的候选组件的BeanDefinition对象。
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        // 初始化一个LinkedHashSet来存储BeanDefinition对象，保证元素的插入顺序。
        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        // 使用ClassUtil工具类扫描指定包下所有使用@Component注解的类。
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);

        // 遍历扫描到的类，为每个类创建一个BeanDefinition对象，并添加到candidates集合中。
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }

        // 返回包含所有候选组件BeanDefinition的集合。
        return candidates;
    }
}
