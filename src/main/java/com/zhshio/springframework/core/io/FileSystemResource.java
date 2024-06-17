package com.zhshio.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:19
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
/**
 * 表示文件系统中的资源。
 * 该类实现了Resource接口，提供了访问文件系统中特定文件资源的方法。
 */
public class FileSystemResource implements Resource{

    /**
     * 文件系统中的文件对象。
     */
    private final File file;

    /**
     * 文件的路径。
     */
    private final String path;

    /**
     * 通过File对象初始化FileSystemResource。
     *
     * @param file 文件系统中的文件对象。
     */
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    /**
     * 通过文件路径初始化FileSystemResource。
     *
     * @param path 文件的路径。
     */
    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * 获取文件的输入流，用于读取文件内容。
     *
     * @return 文件的输入流。
     * @throws IOException 如果打开输入流失败。
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * 获取文件的路径。
     *
     * @return 文件的路径。
     */
    public final String getPath() {
        return this.path;
    }
}
