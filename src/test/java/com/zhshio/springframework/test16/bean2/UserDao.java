package com.zhshio.springframework.test16.bean2;


import com.zhshio.springframework.context.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("2", "张帅");
        hashMap.put("3", "超级暴龙兽进化");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
