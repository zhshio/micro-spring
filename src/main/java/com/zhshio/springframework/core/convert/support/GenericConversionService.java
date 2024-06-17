package com.zhshio.springframework.core.convert.support;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:38
 * @Description: com.zhshio.springframework.core.convert.support
 * @version: 1.0
 */

import com.zhshio.springframework.core.convert.ConversionService;
import com.zhshio.springframework.core.convert.converter.Converter;
import com.zhshio.springframework.core.convert.converter.ConverterFactory;
import com.zhshio.springframework.core.convert.converter.ConverterRegistry;
import com.zhshio.springframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:38
 */

/**
 * 实现了ConversionService和ConverterRegistry接口，提供了一种注册和使用转换器的机制。
 * 它允许在应用程序中注册自定义的转换器，以支持类型之间的转换。
 */
public class GenericConversionService implements ConversionService, ConverterRegistry {

    /**
     * 存储所有注册的转换器，键为ConvertiblePair，值为转换器。
     */
    private Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new HashMap<>();

    /**
     * 检查是否可以转换指定的源类型到目标类型。
     *
     * @param sourceType 源类型的Class对象
     * @param targetType 目标的Class对象
     * @return 如果存在转换器可以转换这两种类型，则返回true；否则返回false。
     */
    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        GenericConverter converter = getConverter(sourceType, targetType);
        return converter != null;
    }

    /**
     * 将源对象转换为目标类型。
     *
     * @param source 源对象
     * @param targetType 目标类型Class对象
     * @param <T> 目标类型的泛型参数
     * @return 转换后的目标类型对象
     */
    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        Class<?> sourceType = source.getClass();
        GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    /**
     * 注册一个通用转换器。
     *
     * @param converter 要注册的转换器
     */
    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair typeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(typeInfo, converter);
        for (GenericConverter.ConvertiblePair convertibleType : converterAdapter.getConvertibleTypes()) {
            converters.put(convertibleType, converterAdapter);
        }
    }

    /**
     * 注册一个转换器工厂。
     *
     * @param converterFactory 要注册的转换器工厂
     */
    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        GenericConverter.ConvertiblePair typeInfo = getRequiredTypeInfo(converterFactory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo, converterFactory);
        for (GenericConverter.ConvertiblePair convertibleType : converterFactoryAdapter.getConvertibleTypes()) {
            converters.put(convertibleType, converterFactoryAdapter);
        }
    }

    /**
     * 注册一个通用转换器。
     *
     * @param converter 要注册的通用转换器
     */
    @Override
    public void addConverter(GenericConverter converter) {
        for (GenericConverter.ConvertiblePair convertibleType : converter.getConvertibleTypes()) {
            converters.put(convertibleType, converter);
        }
    }

    /**
     * 获取转换器所需的类型信息。
     *
     * @param object 转换器或转换器工厂对象
     * @return 类型信息的ConvertiblePair对象
     */
    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object) {
        Type[] types = object.getClass().getGenericInterfaces();
        ParameterizedType parameterized = (ParameterizedType) types[0];
        Type[] actualTypeArguments = parameterized.getActualTypeArguments();
        Class<?> sourceType = (Class<?>) actualTypeArguments[0];
        Class<?> targetType = (Class<?>) actualTypeArguments[1];
        return new GenericConverter.ConvertiblePair(sourceType, targetType);
    }

    /**
     * 获取能够处理指定类型的转换器。
     *
     * @param sourceType 源类型的Class对象
     * @param targetType 目标的Class对象
     * @return 如果找到合适的转换器，则返回转换器对象；否则返回null。
     */
    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates) {
            for (Class<?> targetCandidate : targetCandidates) {
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourceCandidate, targetCandidate);
                GenericConverter converter = converters.get(convertiblePair);
                if (converter != null) {
                    return converter;
                }
            }
        }
        return null;
    }

    /**
     * 获取类的继承层次结构。
     *
     * @param clazz 类的Class对象
     * @return 类的继承层次结构列表，从具体类到Object类
     */
    private List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> hierarchy = new ArrayList<>();
        while (clazz != null) {
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchy;
    }

    /**
     * ConverterAdapter类实现了GenericConverter接口，用于包装Converter。
     */
    private final class ConverterAdapter implements GenericConverter {

        private final ConvertiblePair typeInfo;

        private final Converter<Object, Object> converter;

        public ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converter.convert(source);
        }
    }

    /**
     * ConverterFactoryAdapter类实现了GenericConverter接口，用于包装ConverterFactory。
     */
    private final class ConverterFactoryAdapter implements GenericConverter {

        private final ConvertiblePair typeInfo;

        private final ConverterFactory<Object, Object> converterFactory;

        public ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        /**
         * 实现了ConvertibleTypesProvider接口，以提供可转换类型的集合。
         * 该类的作用是定义从一种类型到另一种类型的转换规则，并提供实际的转换逻辑。
         */
        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            /**
             * 返回一个包含单个ConvertiblePair的集合，表示此转换器支持的类型转换。
             * ConvertiblePair表示一种源类型到目标类型的转换关系。
             */
            return Collections.singleton(typeInfo);
        }

        /**
         * 实现了Converter接口的convert方法，用于执行实际的类型转换。
         * 该方法利用ConverterFactory获取针对目标类型的具体转换器，并使用该转换器将源对象转换为目标类型。
         *
         * @param source 待转换的源对象。
         * @param sourceType 源对象的类型。
         * @param targetType 目标类型。
         * @return 转换后的对象。
         */
        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            /**
             * 通过目标类型从ConverterFactory获取相应的转换器，并使用该转换器将源对象转换为目标类型。
             * 这里假设ConverterFactory已经根据目标类型正确地注册并提供了转换器。
             */
            return converterFactory.getConverter(targetType).convert(source);
        }
    }

}
