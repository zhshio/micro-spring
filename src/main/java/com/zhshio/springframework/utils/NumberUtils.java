package com.zhshio.springframework.utils;/**
 * @Auther: 张帅
 * @Date: 2024/2/5 - 02 - 05 - 22:34
 * @Description: com.zhshio.springframework.utils
 * @version: 1.0
 */

import cn.hutool.core.lang.Assert;
import com.sun.istack.internal.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: zs
 * @time: 2024/2/5 22:34
 */

public class NumberUtils {

    // 定义Long类型的最小值常量
    private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

    // 定义Long类型的最大值常量
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    // 定义标准数字类型集合，包括原始类型和其对应的包装类
    public static final Set<Class<?>> STANDARD_NUMBER_TYPES;

    // 初始化STANDARD_NUMBER_TYPES集合，包含所有标准数字类型
    static {
        Set<Class<?>> numberTypes = new HashSet<>(8);
        numberTypes.add(Byte.class);
        numberTypes.add(Short.class);
        numberTypes.add(Integer.class);
        numberTypes.add(Long.class);
        numberTypes.add(BigInteger.class);
        numberTypes.add(Float.class);
        numberTypes.add(Double.class);
        numberTypes.add(BigDecimal.class);
        STANDARD_NUMBER_TYPES = Collections.unmodifiableSet(numberTypes);
    }


    /**
     * 将Number类型转换为指定的类类型。
     * <p>
     * 支持的转换包括基本数值类型（Byte, Short, Integer, Long, BigInteger）和浮点类型（Float, Double）。
     * 此外，也支持将数值转换为BigDecimal类型。
     * <p>
     * 对于非数值类型的Number实例（如BigInteger和BigDecimal），会尝试进行适当的转换，
     * 以确保不丢失精度。如果转换不可行，将抛出IllegalArgumentException。
     *
     * @param number 要转换的Number对象
     * @param targetClass 目标数值类型的Class对象
     * @param <T> 数值类型的泛型参数
     * @return 转换后的数值对象
     * @throws IllegalArgumentException 如果转换不可行或发生溢出
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass)
            throws IllegalArgumentException {

        Assert.notNull(number, "Number must not be null");
        Assert.notNull(targetClass, "Target class must not be null");

        if (targetClass.isInstance(number)) {
            return (T) number;
        }
        else if (Byte.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Byte.valueOf(number.byteValue());
        }
        else if (Short.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Short.valueOf(number.shortValue());
        }
        else if (Integer.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) Integer.valueOf(number.intValue());
        }
        else if (Long.class == targetClass) {
            long value = checkedLongValue(number, targetClass);
            return (T) Long.valueOf(value);
        }
        else if (BigInteger.class == targetClass) {
            if (number instanceof BigDecimal) {
                // do not lose precision - use BigDecimal's own conversion
                return (T) ((BigDecimal) number).toBigInteger();
            }
            else {
                // original value is not a Big* number - use standard long conversion
                return (T) BigInteger.valueOf(number.longValue());
            }
        }
        else if (Float.class == targetClass) {
            return (T) Float.valueOf(number.floatValue());
        }
        else if (Double.class == targetClass) {
            return (T) Double.valueOf(number.doubleValue());
        }
        else if (BigDecimal.class == targetClass) {
            // always use BigDecimal(String) here to avoid unpredictability of BigDecimal(double)
            // (see BigDecimal javadoc for details)
            return (T) new BigDecimal(number.toString());
        }
        else {
            throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                    number.getClass().getName() + "] to unsupported target class [" + targetClass.getName() + "]");
        }
    }

    /**
     * 获取Number的长整型值，检查是否超出long类型的范围。
     *
     * @param number 数字
     * @param targetClass 目标类
     * @return 长整型值
     */
    private static long checkedLongValue(Number number, Class<? extends Number> targetClass) {
        BigInteger bigInt = null;
        if (number instanceof BigInteger) {
            bigInt = (BigInteger) number;
        }
        else if (number instanceof BigDecimal) {
            bigInt = ((BigDecimal) number).toBigInteger();
        }
        // Effectively analogous to JDK 8's BigInteger.longValueExact()
        if (bigInt != null && (bigInt.compareTo(LONG_MIN) < 0 || bigInt.compareTo(LONG_MAX) > 0)) {
            raiseOverflowException(number, targetClass);
        }
        return number.longValue();
    }

    /**
     * 抛出溢出异常。
     *
     * @param number 数字
     * @param targetClass 目标类
     * @throws IllegalArgumentException 如果发生溢出
     */
    private static void raiseOverflowException(Number number, Class<?> targetClass) {
        throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
    }


    /**
     * 将字符串解析为目标数值类型。
     * <p>
     * 支持基本数值类型（Byte, Short, Integer, Long, BigInteger）和浮点类型（Float, Double）。
     * 也支持解析为BigDecimal类型。解析过程会自动移除字符串中的所有空白字符。
     * <p>
     * 如果字符串以十六进制表示（如"0x10"或"0X10"），将按照相应的类型进行解析。
     *
     * @param text 待解析的字符串
     * @param targetClass 目标数值类型的Class对象
     * @param <T> 数值类型的泛型参数
     * @return 解析后的数值对象
     * @throws IllegalArgumentException 如果解析失败或目标类不支持
     */

    public static <T extends Number> T parseNumber(String text, Class<T> targetClass) {
        Assert.notNull(text, "Text must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        String trimmed = trimAllWhitespace(text);

        // 根据目标类类型，选择合适的解析方法
        if (Byte.class == targetClass) {
            return (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
        }
        else if (Short.class == targetClass) {
            return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
        }
        else if (Integer.class == targetClass) {
            return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
        }
        else if (Long.class == targetClass) {
            return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
        }
        else if (BigInteger.class == targetClass) {
            return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
        }
        else if (Float.class == targetClass) {
            return (T) Float.valueOf(trimmed);
        }
        else if (Double.class == targetClass) {
            return (T) Double.valueOf(trimmed);
        }
        else if (BigDecimal.class == targetClass || Number.class == targetClass) {
            return (T) new BigDecimal(trimmed);
        }
        else {
            throw new IllegalArgumentException(
                    "Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
        }
    }


    /**
     * 使用NumberFormat将字符串解析为目标数值类型。
     * <p>
     * 如果提供了NumberFormat，则使用该格式解析字符串。否则，调用parseNumber(String, Class)方法进行解析。
     *
     * @param text 待解析的字符串
     * @param targetClass 目标数值类型的Class对象
     * @param numberFormat 数值格式化对象，可为null
     * @param <T> 数值类型的泛型参数
     * @return 解析后的数值对象
     * @throws IllegalArgumentException 如果解析失败
     */
    public static <T extends Number> T parseNumber(
            String text, Class<T> targetClass, @Nullable NumberFormat numberFormat) {

        // 当提供了自定义数字格式时
        if (numberFormat != null) {
            // 确保文本和目标类不为空
            Assert.notNull(text, "Text must not be null");
            Assert.notNull(targetClass, "Target class must not be null");

            // 若格式器为DecimalFormat且目标类型为BigDecimal，调整解析设置
            DecimalFormat decimalFormat = null;
            boolean resetBigDecimal = false;
            if (numberFormat instanceof DecimalFormat) {
                decimalFormat = (DecimalFormat) numberFormat;
                if (BigDecimal.class == targetClass && !decimalFormat.isParseBigDecimal()) {
                    decimalFormat.setParseBigDecimal(true);
                    resetBigDecimal = true;
                }
            }

            try {
                // 使用提供的格式解析数字
                Number number = numberFormat.parse(trimAllWhitespace(text));
                // 将解析得到的Number转换为目标类型并返回
                return convertNumberToTargetClass(number, targetClass);
            }
            // 捕获解析异常并重新抛出更友好的错误信息
            catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse number: " + ex.getMessage());
            }
            // 保证DecimalFormat状态复原
            finally {
                if (resetBigDecimal) {
                    decimalFormat.setParseBigDecimal(false);
                }
            }
        }
        // 如果没有提供数字格式，调用默认的解析方法
        else {
            return parseNumber(text, targetClass);
        }
    }

    /**
     * 移除字符串中的所有空白字符。
     *
     * @param str 待处理的字符串
     * @return 处理后的字符串
     */
    public static String trimAllWhitespace(String str) {
        // 检查字符串是否为空或null，是则直接返回原字符串
        if (!hasLength(str)) {
            return str;
        }

        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        // 遍历字符串中的每个字符
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            // 如果字符不是空格，则添加到StringBuilder中
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        // 返回移除空格后的字符串
        return sb.toString();
    }

    /**
     * 检查字符串是否非空。
     *
     * @param str 待检查的字符串
     * @return 如果字符串非空返回true，否则返回false
     */
    public static boolean hasLength(@Nullable String str) {
        return (str != null && !str.isEmpty());
    }


    /**
     * 判断字符串是否以十六进制形式表示。
     *
     * @param value 待检查的字符串
     * @return 如果字符串以十六进制形式表示返回true，否则返回false
     */
    private static boolean isHexNumber(String value) {
        int index = (value.startsWith("-") ? 1 : 0);
        return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
    }

    /**
     * 解析十六进制表示的BigInteger。
     * 此方法支持解析带符号位的十六进制、八进制和十进制字符串到BigInteger对象。
     * 符号位以"-"表示负数，十六进制以"0x"或"0X"开头，八进制以"0"开头。
     *
     * @param value 待解析的字符串，可以是十进制、八进制或十六进制表示的数字，可能带符号位。
     * @return 解析后的BigInteger对象，保留原始的符号位。
     */
    /**
     * 解析十六进制表示的BigInteger。
     *
     * @param value 十六进制表示的字符串
     * @return 解析后的BigInteger对象
     */
    private static BigInteger decodeBigInteger(String value) {
        // 默认基数为10，用于后续的BigInteger构造
        int radix = 10;
        // 字符串索引，用于遍历字符串检查特殊语法
        int index = 0;
        // 标记是否为负数
        boolean negative = false;

        // 检查是否带负号
        // Handle minus sign, if present.
        if (value.startsWith("-")) {
            negative = true;
            index++;
        }

        // 检查是否为十六进制，支持"0x"或"0X"以及"#"开头
        // Handle radix specifier, if present.
        if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        }
        else if (value.startsWith("#", index)) {
            index++;
            radix = 16;
        }
        // 检查是否为八进制，以"0"开头
        else if (value.startsWith("0", index) && value.length() > 1 + index) {
            index++;
            radix = 8;
        }

        // 使用确定的基数和子字符串构造BigInteger对象
        BigInteger result = new BigInteger(value.substring(index), radix);
        // 根据负号标记，返回对应的正负BigInteger对象
        return (negative ? result.negate() : result);
    }

}
