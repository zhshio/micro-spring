package com.zhshio.springframework.beans;

/**
 * @Auther: 张帅
 * @Date: 2023/11/9 - 11 - 09 - 20:04
 * @Description: com.zhshio.springframework.beans
 * @version: 1.0
 */

/**
 * BeansException类是运行时异常的子类，用于处理与Bean相关的异常。
 */
public class BeansException extends RuntimeException {

    /**
     * 构造函数，接收一个字符串参数作为异常信息。
     *
     * @param msg 异常信息字符串。
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * 构造函数，接收一个字符串参数作为异常信息和一个Throwable参数作为异常原因。
     *
     * @param msg 异常信息字符串。
     * @param cause 异常的原因。
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
