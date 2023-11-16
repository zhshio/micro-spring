package com.zhshio.springframework.test.bean;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: com.zhshio.springframework.test.bean.UserService
 * @version: 1.0
 */
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
