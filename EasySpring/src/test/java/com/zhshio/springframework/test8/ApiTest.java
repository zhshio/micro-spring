package com.zhshio.springframework.test8;

import cn.hutool.core.io.IoUtil;
import com.zhshio.springframework.context.support.ClassPathXmlApplicationContext;
import com.zhshio.springframework.core.io.DefaultResourceLoader;
import com.zhshio.springframework.core.io.Resource;
import com.zhshio.springframework.test8.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }


    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test8/spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

}
