package com.zhshio.springframework.test8.bean;

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
        System.out.println("执行: UserDao init-method");
        hashMap.put("1", "Shio");
        hashMap.put("2", "张帅");
        hashMap.put("3", "超级暴龙兽进化");
    }

    public void destroyUserDao() {
        System.out.println("执行: UserDao destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }

}
