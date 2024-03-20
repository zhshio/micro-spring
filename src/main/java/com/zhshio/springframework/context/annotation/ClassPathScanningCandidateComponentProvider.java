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

public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
