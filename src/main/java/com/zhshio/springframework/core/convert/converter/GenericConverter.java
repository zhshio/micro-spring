package com.zhshio.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:30
 * @Description: com.zhshio.springframework.core.convert
 * @version: 1.0
 */
/**
 * 通用转换器接口，定义了将一种类型转换为另一种类型的逻辑。
 */
public interface GenericConverter {

    /**
     * 获取支持的转换类型对集合。
     *
     * @return 转换类型对的集合，每个类型对由源类型和目标类型组成。
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * 将给定的源对象转换为目标类型。
     *
     * @param source 源对象，需要进行类型转换。
     * @param sourceType 源对象的类类型。
     * @param targetType 目标类型，源对象将被转换为此类型。
     * @return 转换后的对象，类型为目标类型。
     */
    Object convert(Object source, Class sourceType, Class targetType);

    /**
     * 表示一对可转换的类型，即从一个类型转换到另一个类型。
     */
    final class ConvertiblePair {
        private final Class<?> sourceType;

        private final Class<?> targetType;

        /**
         * 创建一个可转换类型对。
         *
         * @param sourceType 源类型，不能为空。
         * @param targetType 目标类型，不能为空。
         */
        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        /**
         * 获取源类型。
         *
         * @return 源类型的Class对象。
         */
        public Class<?> getSourceType() {
            return this.sourceType;
        }

        /**
         * 获取目标类型。
         *
         * @return 目标的Class对象。
         */
        public Class<?> getTargetType() {
            return this.targetType;
        }

        /**
         * 判断当前对象与另一个对象是否相等。
         * 相等的定义是两个对象的源类型和目标类型都相同。
         *
         * @param obj 另一个对象，用于比较是否相等。
         * @return 如果两个对象的源类型和目标类型都相同，则返回true；否则返回false。
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
        }

        /**
         * 计算当前对象的哈希码。
         * 哈希码的计算基于源类型的哈希码和目标类型的哈希码的组合。
         *
         * @return 当前对象的哈希码。
         */
        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }

}
