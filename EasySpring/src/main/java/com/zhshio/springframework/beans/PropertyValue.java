package com.zhshio.springframework.beans;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:17
 * @Description: com.zhshio.springframework.beans.factory
 * @version: 1.0
 */
/**
 * 属性值类，用于封装属性的名称和值。
 */
public class PropertyValue {
    private final String name; // 属性名
    private final Object value; // 属性值

    /**
     * 构造函数，创建一个新的PropertyValue实例。
     *
     * @param name 属性的名称，类型为String。
     * @param value 属性的值，类型为Object。
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取属性的名称。
     *
     * @return 返回属性的名称，类型为String。
     */
    public String getName() {
        return name;
    }

    /**
     * 获取属性的值。
     *
     * @return 返回属性的值，类型为Object。
     */
    public Object getValue() {
        return value;
    }

}
