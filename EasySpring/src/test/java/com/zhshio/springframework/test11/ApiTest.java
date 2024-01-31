package com.zhshio.springframework.test11;


import com.zhshio.springframework.context.support.ClassPathXmlApplicationContext;
import com.zhshio.springframework.test11.event.CustomEvent;
import org.junit.Test;


public class ApiTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:test11/spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

}
