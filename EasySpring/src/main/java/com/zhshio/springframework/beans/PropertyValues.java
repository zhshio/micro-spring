package com.zhshio.springframework.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 张帅
 * @Date: 2023/11/17 - 11 - 17 - 19:17
 * @Description: com.zhshio.springframework.beans
 * @version: 1.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new LinkedList<>();


    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
