package com.zhshio.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanReference;
import com.zhshio.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.zhshio.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.zhshio.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:14
 * @Description: com.zhshio.springframework.beans.factory.xml
 * @version: 1.0
 */

/**
 * 该类是一个用于从XML文件中加载Bean定义的类，它继承了AbstractBeanDefinitionReader类。
 * 它提供了多个构造函数，用于初始化Bean定义注册表和资源加载器。
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 构造函数，初始化Bean定义注册表。
     *
     * @param registry Bean定义注册表，用于注册解析出的Bean定义。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 构造函数，初始化Bean定义注册表和资源加载器。
     *
     * @param registry Bean定义注册表。
     * @param resourceLoader 资源加载器，用于加载XML资源。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从指定资源加载Bean定义。
     *
     * @param resource 资源，通常是一个XML文件。
     * @throws BeansException 如果加载过程中发生错误。
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 从多个指定资源加载Bean定义。
     *
     * @param resources 多个资源。
     * @throws BeansException 如果加载过程中发生错误。
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 从指定位置加载Bean定义。
     *
     * @param location 资源位置，可以是一个文件路径或URL。
     * @throws BeansException 如果加载过程中发生错误。
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 从多个指定位置加载Bean定义。
     *
     * @param locations 多个资源位置。
     * @throws BeansException 如果加载过程中发生错误。
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 真正加载Bean定义的方法，从输入流解析XML文件并处理。
     *
     * @param inputStream 输入流，通常来自一个XML文件。
     * @throws ClassNotFoundException 如果指定的类找不到。
     * @throws DocumentException 如果解析XML文件出错。
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {

        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();


        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            // 解析bean元素，包括id、name、class等属性
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            // 获取类，用于创建BeanDefinition
            Class<?> clazz = Class.forName(className);
            // 为Bean定义名称
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            // 创建BeanDefinition并设置初始化和销毁方法
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            // 解析并设置bean的属性
            List<Element> propertyList = bean.elements("property");
            for (Element property : propertyList) {
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // 注册BeanDefinition
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }


    /**
     * 扫描指定包下的类，注册为BeanDefinition。
     *
     * @param scanPath 要扫描的包路径，可以是多个包。
     */
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }


}
