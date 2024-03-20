package com.zhshio.springframework.beans.factory;/**
 * @Auther: 张帅
 * @Date: 2024/2/2 - 02 - 02 - 9:51
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.PropertyValues;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zhshio.springframework.core.io.DefaultResourceLoader;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/2 9:51
 */

/**
 * 用于配置属性占位符的类，实现BeanFactoryPostProcessor接口，能够在Spring容器加载Bean定义之后，
 * 对Bean定义中的属性值进行占位符替换。
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${"; // 默认的占位符前缀

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}"; // 默认的占位符后缀

    private String location; // 属性文件的位置

    /**
     * 处理Bean工厂，在Spring加载Bean定义之后，替换Bean定义中的属性值的占位符。
     *
     * @param beanFactory Bean工厂，用于访问和修改Bean定义。
     * @throws BeansException 如果处理过程中发生错误。
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        try {
            // 加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            // 读取属性文件内容
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            // 遍历所有Bean定义，替换占位符
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    // 替换占位符
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }

            // 为容器添加字符串解析器，支持@Value注解的解析
            StringValueResolver valueResolver = new PlacehodlerResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);


        } catch (IOException e) {
            throw new BeansException("Could not load properties",e);
        }
    }

    /**
     * 解析属性值中的占位符，用属性文件中的值进行替换。
     *
     * @param value 需要解析的属性值字符串。
     * @param properties 属性文件的内容。
     * @return 替换占位符后的属性值字符串。
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            // 提取占位符中的属性键，并查找其对应的属性值
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            // 替换占位符
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }

    /**
     * 设置属性文件的位置。
     *
     * @param location 属性文件的位置。
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 内部类，实现StringValueResolver接口，用于解析字符串中的占位符。
     */
    private class PlacehodlerResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties; // 属性文件的内容

        /**
         * 构造函数。
         *
         * @param properties 属性文件的内容。
         */
        private PlacehodlerResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        /**
         * 解析字符串中的占位符，使用属性文件中的值进行替换。
         *
         * @param strVal 需要解析的字符串。
         * @return 替换占位符后的字符串。
         */
        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }
}
