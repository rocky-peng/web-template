package com.commons.utils;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @author pengqingsong
 * @date 07/09/2017
 * @desc 从map读取某个key的工具类
 */
public class MapUtils {

    public static Byte getByte(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Byte.valueOf(map.get(field).toString());
    }

    public static Short getShort(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Short.valueOf(map.get(field).toString());
    }

    public static Integer getInteger(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Integer.valueOf(map.get(field).toString());
    }

    public static Long getLong(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Long.valueOf(map.get(field).toString());
    }

    public static Float getFloat(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Float.valueOf(map.get(field).toString());
    }

    public static Double getDouble(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : Double.valueOf(map.get(field).toString());
    }

    public static String getString(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field) == null ? null : map.get(field).toString();
    }

    public static Object getObject(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return map.get(field);
    }

    public static Long getLongOfTimestamp(Map<String, Object> map, String field) {
        Timestamp timestamp = getTimestamp(map, field);
        return timestamp == null ? null : timestamp.getTime();
    }

    public static Timestamp getTimestamp(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return (Timestamp) map.get(field);
    }

    public static BigDecimal getBigDecimal(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return (BigDecimal) map.get(field);
    }

    public static Date getDate(Map<String, Object> map, String field) {
        if (map == null) {
            return null;
        }
        return (Date) map.get(field);
    }

    public static <T> T getValue(Map<String, Object> map, String field, Class<T> targetFieldType) {
        if (map == null) {
            return null;
        }

        if (targetFieldType == Byte.class) {
            return (T) getByte(map, field);
        }
        if (targetFieldType == Short.class) {
            return (T) getShort(map, field);
        }
        if (targetFieldType == Integer.class) {
            return (T) getInteger(map, field);
        }
        if (targetFieldType == Long.class) {
            return (T) getLong(map, field);
        }
        if (targetFieldType == Float.class) {
            return (T) getFloat(map, field);
        }
        if (targetFieldType == Double.class) {
            return (T) getDouble(map, field);
        }
        if (targetFieldType == String.class) {
            return (T) getString(map, field);
        }
        if (targetFieldType == BigDecimal.class) {
            return (T) getBigDecimal(map, field);
        }
        if (targetFieldType == Date.class ||
                targetFieldType == Timestamp.class ||
                targetFieldType == java.sql.Date.class ||
                targetFieldType == Time.class) {
            return (T) getObject(map, field);
        }


        throw new RuntimeException("不支持的数据类型");
    }

    public static <T> T convert(Object value, Class<T> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType == Byte.class) {
            return (T) Byte.valueOf(value.toString());
        }
        if (targetType == Short.class) {
            return (T) Short.valueOf(value.toString());
        }
        if (targetType == Integer.class) {
            return (T) Integer.valueOf(value.toString());
        }
        if (targetType == Long.class) {
            return (T) Long.valueOf(value.toString());
        }
        if (targetType == Float.class) {
            return (T) Float.valueOf(value.toString());
        }
        if (targetType == Double.class) {
            return (T) Double.valueOf(value.toString());
        }
        if (targetType == String.class) {
            return (T) value.toString();
        }

        if (targetType == Object.class ||
                targetType == Date.class ||
                targetType == Timestamp.class ||
                targetType == java.sql.Date.class ||
                targetType == Time.class ||
                targetType == BigDecimal.class) {
            return (T) value;
        }

        throw new RuntimeException("不支持的数据类型");
    }
}
