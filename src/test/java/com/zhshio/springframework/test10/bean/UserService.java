package com.zhshio.springframework.test10.bean;

import com.zhshio.springframework.beans.BeansException;
import com.zhshio.springframework.beans.factory.BeanClassLoaderAware;
import com.zhshio.springframework.beans.factory.BeanFactory;
import com.zhshio.springframework.beans.factory.BeanFactoryAware;
import com.zhshio.springframework.beans.factory.BeanNameAware;
import com.zhshio.springframework.context.ApplicationContext;
import com.zhshio.springframework.context.ApplicationContextAware;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 18:15
 * @Description: com.zhshio.springframework.test.bean.UserService
 * @version: 1.0
 */
public class UserService {

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
