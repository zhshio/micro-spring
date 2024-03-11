package com.zhshio.springframework.beans.factory.support;

import cn.hutool.db.handler.HandleHelper;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 19:57
 * @Description: com.zhshio.springframework.beans.factory.support
 * @version: 1.0
 */
/**
 * 默认可列表Bean工厂类，继承自AbstractAutowireCapableBeanFactory，实现了BeanDefinitionRegistry和ConfigurableListableBeanFactory接口。
 * 用于管理Bean定义和实例。
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    /**
     * 存储Bean定义的Map，键为Bean名称，值为Bean定义。
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册一个Bean定义到工厂中。
     *
     * @param beanName 要注册的Bean的名称。
     * @param beanDefinition 要注册的Bean的定义。
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 检查工厂中是否包含指定名称的Bean定义。
     *
     * @param beanName 要检查的Bean的名称。
     * @return 如果包含Bean定义则返回true，否则返回false。
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 获取指定名称的Bean定义。
     *
     * @param beanName 要获取定义的Bean的名称。
     * @return 对应的Bean定义。
     * @throws BeansException 如果没有找到对应的Bean定义。
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    /**
     * 预实例化所有单例Bean。
     *
     * @throws BeansException 如果实例化过程中发生错误。
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().stream().forEach(this::getBean);
    }

    /**
     * 根据类型获取所有匹配的Bean实例。
     *
     * @param type 要获取的Bean的类型。
     * @return 包含所有匹配Bean实例的Map，键为Bean名称，值为Bean实例。
     * @throws BeansException 如果获取过程中发生错误。
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, BeanDefinition) -> {
           Class beanClass = BeanDefinition.getBeanClass();
           if (type.isAssignableFrom(beanClass)) {
               result.put(beanName, (T) getBean(beanName));
           }
        });
        return result;
    }

    /**
     * 获取工厂中所有Bean定义的名称。
     *
     * @return 包含所有Bean定义名称的数组。
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    /**
     * 根据给定的类型获取单个bean实例。如果发现多个匹配的bean，则会抛出异常。
     *
     * @param requiredType 需要的bean的类型。
     * @return 与给定类型匹配的单个bean实例。
     * @throws BeansException 如果找不到匹配的bean或找到多个匹配的bean。
     */
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        // 遍历所有的bean定义，查找类型匹配的bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            // 如果bean类匹配，则将bean名称添加到列表中
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        // 如果只有一个匹配的bean，就获取并返回这个bean
        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }

        // 如果找到的bean数量不符合预期，抛出异常
        throw new BeansException(requiredType + " expected single bean but found " + beanNames.size() + ": " + beanNames);
    }

}
