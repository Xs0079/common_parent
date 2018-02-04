package com.yxp.common.db.manyDatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManyDataSourceSwitch {

    private static Logger logger = LoggerFactory.getLogger(ManyDataSourceSwitch.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSourceType(String dataSourceType) {
        logger.debug("set datasource : {}", dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        logger.debug("get datasource : {}", contextHolder.get());
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}
