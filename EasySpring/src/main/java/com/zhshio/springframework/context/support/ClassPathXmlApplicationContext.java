package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * @description: 从 XML 中加载 BeanDefintion, 并刷新上下文
     * @author: 张帅
     * @date: 2024/1/3 22:30
     * @param: [configLocations]
     * @return: [java.lang.String]
     **/
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }


    /**
     * @description: 从 XML 中加载 BeanDefinition, 并刷新上下文
     * @author: 张帅
     * @date: 2024/1/3 22:31
     * @param: [configLocations]
     * @return: [java.lang.String[]]
     **/
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
