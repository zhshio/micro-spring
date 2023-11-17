package com.zhshio.springframework.beans;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:17
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
public class PropertyValue {
    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
