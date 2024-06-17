package com.zhshio.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.zhshio.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:18
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */

/**
 * 表示类路径资源的类，提供访问类路径上的资源的功能。
 */
public class ClassPathResource implements Resource{

    /**
     * 资源的路径。
     */
    private final String path;

    /**
     * 用于加载资源的类加载器。
     */
    private ClassLoader classLoader;

    /**
     * 构造一个新的ClassPathResource实例。
     * 使用默认的类加载器来加载资源。
     *
     * @param path 资源的路径，必须不为null。
     */
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 构造一个新的ClassPathResource实例。
     *
     * @param path 资源的路径，必须不为null。
     * @param classLoader 用于加载资源的类加载器，如果为null，则使用默认的类加载器。
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流。
     * @throws IOException 如果无法打开输入流。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
