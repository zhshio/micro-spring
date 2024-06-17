package com.zhshio.springframework.context;

import com.zhshio.springframework.beans.BeansException;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context.support
 * @version: 1.0
 */
/**
 * ConfigurableApplicationContext 接口扩展了 ApplicationContext 接口，添加了对应用程序上下文的配置和管理功能。
 * 这些功能包括刷新上下文、注册关闭钩子和关闭上下文。
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新应用程序上下文。
     * 这个方法初始化应用程序上下文，包括解析和实例化beans，以及初始化它们的依赖关系。
     * <p>
     * 注意：此方法可能抛出BeansException，表示在初始化beans的过程中遇到了错误。
     *
     * @throws BeansException 如果在刷新上下文过程中出现错误。
     */
    void refresh() throws BeansException;

    /**
     * 注册一个JVM关闭钩子，以便在JVM关闭时优雅地关闭应用程序上下文。
     * 这确保了在应用程序退出时，所有必要的清理工作（如关闭单例beans）都能被正确执行。
     */
    void registerShutdownHook();

    /**
     * 关闭应用程序上下文。
     * 这个方法触发应用程序上下文的关闭流程，包括调用所有实现了DisposableBean接口的beans的destroy方法。
     * <p>
     * 注意：调用此方法后，应用程序上下文不应再被使用，因为它已经进入了关闭状态。
     */
    void close();
}

