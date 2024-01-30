package com.zhshio.springframework.test8.bean;

import com.zhshio.springframework.beans.factory.DisposableBean;
import com.zhshio.springframework.beans.factory.InitializingBean;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: com.zhshio.springframework.test.bean.UserService
 * @version: 1.0
 */
public class UserService implements InitializingBean, DisposableBean {

    private String id;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(id)+", 公司：" + company + ", 地点" + location;
    }

    public String getuId() {
        return id;
    }

    public void setuId(String uId) {
        this.id = id;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行: UserService destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行: UserService afterPropertiesSet方法");
    }
}
