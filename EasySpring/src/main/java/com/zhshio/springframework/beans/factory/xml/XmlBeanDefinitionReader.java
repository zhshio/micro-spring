package com.zhshio.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.PropertyValue;
import com.zhshio.springframework.beans.factory.config.BeanDefinition;
import com.zhshio.springframework.beans.factory.config.BeanReference;
import com.zhshio.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.zhshio.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:14
 * @Description: com.zhshio.springframework.beans.factory.xml
 * @version: 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // 读取 XML 文件并创建 Document 对象
        Document doc = XmlUtil.readXML(inputStream);

        // 获取根元素
        Element root = doc.getDocumentElement();

        // 获取根元素的子节点列表
        NodeList childNodes = root.getChildNodes();

        // 遍历子节点
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断子节点是否为元素节点，如果不是则跳过
            if (!(childNodes.item(i) instanceof Element)) continue;

            // 获取当前子节点
            Element bean = (Element) childNodes.item(i);

            // 判断当前子节点是否为 "bean" 元素，如果不是则跳过
            if (!"bean".equals(bean.getNodeName())) continue;

            // 解析 "bean" 元素，获取 id、name 和 class 属性的值
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 获取 Class 对象
            Class<?> clazz = Class.forName(className);

            // 根据 id、name 的值确定 bean 的名称，如果都为空，则使用类名的首字母小写作为 bean 的名称
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 创建 BeanDefinition 对象，传入 Class 对象作为参数
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            // 遍历 "bean" 元素的子节点
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                // 判断子节点是否为元素节点，如果不是则跳过
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;

                // 判断子节点是否为 "property" 元素，如果不是则跳过
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                // 解析 "property" 元素，获取 name、value 和 ref 属性的值
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;

                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);

                // 将属性信息添加到 BeanDefinition 的属性值列表中
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // 检查已注册的 BeanDefinition 中是否存在相同的 beanName，如果存在则抛出异常
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 将当前的 BeanDefinition 注册到 BeanDefinitionRegistry 中，使用 beanName 作为键
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
