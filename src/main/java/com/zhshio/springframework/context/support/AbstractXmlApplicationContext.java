package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zhshio.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhshio.springframework.context.ConfigurableApplicationContext;
import com.zhshio.springframework.core.io.DefaultResourceLoader;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:30
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
/**
 * 抽象的XML应用程序上下文类，扩展了AbstractRefreshableApplicationContext类。
 * 该类提供了加载XML配置文件中定义的bean定义的具体实现。
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载bean定义的方法。通过XML文件加载bean定义到给定的bean工厂中。
     *
     * @param beanFactory 用于存储bean定义的bean工厂。
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 创建XML bean定义读取器，用于读取和解析XML配置文件。
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        // 获取配置文件位置。
        String[] configLocations = getConfigLocations();
        // 如果配置文件位置不为空，则加载bean定义。
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置文件的位置。这是一个抽象方法，需要在子类中实现。
     *
     * @return 配置文件的路径数组。
     */
    protected abstract String[] getConfigLocations();

}

