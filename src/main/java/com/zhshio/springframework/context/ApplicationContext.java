package com.zhshio.springframework.context;


import com.zhshio.springframework.beans.factory.HierarchicalBeanFactory;
import com.zhshio.springframework.beans.factory.ListableBeanFactory;
import com.zhshio.springframework.core.io.ResourceLoader;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 23:31
 * @Description: com.zhshio.springframework.context
 * @version: 1.0
 */
/**
 * 应用程序上下文接口，提供了访问和管理应用程序中所有bean的能力。
 * 该接口扩展了ListableBeanFactory、HierarchicalBeanFactory、ResourceLoader和ApplicationEventPublisher接口，
 * 提供了更丰富的功能，包括bean的创建、依赖解析、事件发布等。
 *
 * 作为应用程序上下文，它扮演着核心角色，提供了访问应用程序中bean的途径，
 * 以及管理bean生命周期的方法。通过实现这个接口，可以实现对应用程序状态的全面控制和管理。
 *
 * @see ListableBeanFactory 用于列出和访问所有bean的能力
 * @see HierarchicalBeanFactory 支持bean工厂的层级结构
 * @see ResourceLoader 提供加载资源的能力
 * @see ApplicationEventPublisher 支持发布和处理应用程序事件
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
    //目前还不需要添获取ID和父类上下文，暂无接口方法的定义。
}
