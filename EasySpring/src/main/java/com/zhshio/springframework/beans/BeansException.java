package com.zhshio.springframework.beans;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 20:04
 * @Description: com.zhshio.springframework.beans
 * @version: 1.0
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}