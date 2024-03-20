package com.zhshio.springframework.test16;


import com.zhshio.springframework.context.support.ClassPathXmlApplicationContext;
import com.zhshio.springframework.test16.bean.IUserService;
import com.zhshio.springframework.test16.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test16/spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
    @Test
    public void test_autoProxy_2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test16/spring2.xml");
        com.zhshio.springframework.test16.bean2.IUserService userService = applicationContext.getBean("userService",  com.zhshio.springframework.test16.bean2.IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
