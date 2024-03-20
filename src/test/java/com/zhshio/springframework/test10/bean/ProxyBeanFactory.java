package com.zhshio.springframework.test10.bean;/**
 * @Auther: 张帅
 * @Date: 2024/1/31 - 01 - 31 - 10:04
 * @Description: com.zhshio.springframework.test10.bean
 * @version: 1.0
 */

import com.zhshio.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zs
 * @time: 2024/1/31 10:04
 */

public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "Shio");
            hashMap.put("2", "张帅");
            hashMap.put("3", "超级暴龙兽进化");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
