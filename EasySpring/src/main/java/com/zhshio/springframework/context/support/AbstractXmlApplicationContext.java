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
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
