package com.zhshio.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:19
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
/**
 * Resource接口定义了一个资源，该资源可以被获取为一个输入流。
 * 这个接口的目的是提供一种抽象的方式来访问各种类型的资源，无论它们是以何种形式存在的（如文件、数据库记录等）。
 * 通过统一提供获取输入流的方法，使得任何实现了这个接口的类都可以被一致地处理，而无需关心具体的资源类型。
 */
public interface Resource {
    /**
     * 获取这个资源的输入流。
     * 通过这个方法，可以以流的形式访问资源的内容，这对于读取大文件或远程资源特别有用。
     *
     * @return 输入流，用于读取资源的内容。
     * @throws IOException 如果在获取输入流的过程中发生错误，比如资源不存在或没有访问权限。
     */
    InputStream getInputStream() throws IOException;
}
