package com.zhshio.springframework.core.io;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:19
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
/**
 * 资源加载器接口，用于按位置获取资源。
 * 提供了对类路径下资源的统一访问能力。
 */
public interface ResourceLoader {

    /**
     * 类路径资源的URL前缀。
     * 用于标识资源位置是类路径下的，配合getResource方法使用。
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据给定的位置获取资源。
     *
     * @param location 资源的位置，可以是类路径或文件系统中的路径。
     * @return 代表指定位置的资源对象。
     * 注意：该方法的具体实现可能支持不同的位置格式，如类路径或文件系统路径。
     */
    Resource getResource(String location);

}

