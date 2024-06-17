package com.zhshio.springframework.context.support;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
/**
 * ClassPathXmlApplicationContext 类继承自 AbstractXmlApplicationContext，
 * 提供了从类路径下的XML配置文件中加载和管理Bean的实现。
 * 这个类的主要作用是初始化和管理Spring应用程序上下文，
 * 通过从指定的类路径下的XML配置文件中读取Bean的定义并创建它们。
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    // 存储配置文件位置的数组
    private String[] configLocations;

    /**
     * 默认构造函数，不接受任何参数。
     */
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 单个配置文件路径的构造函数。
     *
     * @param configLocations 配置文件的路径，相对于类路径。
     * @throws BeansException 如果在初始化上下文过程中出现错误。
     */
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
     * 多个配置文件路径的构造函数。
     *
     * @param configLocations 配置文件路径的数组，相对于类路径。
     * @throws BeansException 如果在初始化上下文过程中出现错误。
     */
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

    /**
     * 获取配置文件的位置。
     *
     * @return 配置文件的路径数组。
     */
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
