package com.zhshio.springframework.test15;


import com.zhshio.springframework.context.support.ClassPathXmlApplicationContext;
import com.zhshio.springframework.test15.bean.IUserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test15/spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }


}
