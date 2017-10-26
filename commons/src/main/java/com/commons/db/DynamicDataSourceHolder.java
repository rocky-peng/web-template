package com.commons.db;

public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> HOLDER = new ThreadLocal<>();
    private static final String MASTER = "master";
    private static final String SLAVE = "slave";

    public static void chooseMaster() {
        HOLDER.set(MASTER);
    }

    public static void chooseSlave() {
        HOLDER.set(SLAVE);
    }

    public static String getDataSouce() {
        return HOLDER.get();
    }

    public static void reset() {
        HOLDER.remove();
    }
}