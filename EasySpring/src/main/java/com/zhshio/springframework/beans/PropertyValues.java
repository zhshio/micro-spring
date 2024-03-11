package com.zhshio.springframework.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:17
 * @Description: com.zhshio.springframework.beans
 * @version: 1.0
 */
/**
 * 属性值容器类，用于管理和存储一系列属性值对象。
 */
public class PropertyValues {

    // 存储属性值对象的列表
    private final List<PropertyValue> propertyValueList = new LinkedList<>();

    /**
     * 向容器中添加一个属性值对象。
     *
     * @param propertyValue 要添加的属性值对象。
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    /**
     * 获取所有属性值对象的数组。
     *
     * @return 包含所有属性值对象的数组。
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名获取对应的属性值对象。
     *
     * @param propertyName 要查找的属性名。
     * @return 如果找到具有匹配名称的属性值对象，则返回该对象；否则返回null。
     */
    public PropertyValue getPropertyValue(String propertyName) {
        // 遍历列表，查找名称匹配的属性值对象
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
