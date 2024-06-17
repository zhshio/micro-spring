package com.zhshio.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:19
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
/**
 * UrlResource类实现了Resource接口，代表了一个通过URL访问的资源。
 * 它提供了打开并读取资源输入流的方法。
 */
public class UrlResource implements Resource{
    /**
     * 存储资源的URL。
     */
    private final URL url;

    /**
     * 构造一个新的UrlResource实例。
     *
     * @param url 资源的URL，不能为null。
     * @throws IllegalArgumentException 如果url为null，则抛出此异常。
     */
    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null");
        this.url = url;
    }

    /**
     * 打开并返回一个输入流，用于读取资源的内容。
     *
     * @return 用于读取资源内容的InputStream。
     * @throws IOException 如果打开或读取资源时发生错误，则抛出此异常。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        }
        catch (IOException ex){
            // 如果连接是HttpURLConnection类型，则断开连接。
            if (con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}
