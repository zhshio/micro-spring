package com.zhshio.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 张帅
 * @Date: 2023/11/18 - 11 - 18 - 17:19
 * @Description: com.zhshio.springframework.core.io
 * @version: 1.0
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
