package com.zhshio.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 20:22
 * @Description: com.zhshio.springframework.test.bean
 * @version: 1.0
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initUserDao() {
        System.out.println("执行：init-method");
        hashMap.put("10001", "zs");
        hashMap.put("10002", "hzq");
        hashMap.put("10003", "hhd");
    }

    public void destroyUserDao() {
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }

}
