package com.zhshio.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:18
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
/**
 * 默认资源加载器，实现ResourceLoader接口，提供资源的加载能力。
 */
public class DefaultResourceLoader implements ResourceLoader{
    /**
     * 根据给定的位置字符串加载资源。
     *
     * @param location 资源的位置字符串，可以是类路径或文件系统路径。
     * @return 对应的Resource对象。
     * @throws IllegalArgumentException 如果位置字符串为null。
     */
    @Override
    public Resource getResource(String location) {
        // 确保位置字符串不为空
        Assert.notNull(location, "Location must not be null");
        // 如果位置字符串以classpath:开头，表示是类路径资源
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 截取掉classpath:前缀，使用ClassPathResource加载资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                // 尝试将位置字符串解析为URL，如果是有效的URL，则使用UrlResource加载资源
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 如果位置字符串不是一个有效的URL，尝试将其作为文件系统路径，使用FileSystemResource加载资源
                return new FileSystemResource(location);
            }
        }
    }
}

