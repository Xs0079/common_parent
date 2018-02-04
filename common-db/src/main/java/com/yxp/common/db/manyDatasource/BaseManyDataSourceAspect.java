package com.yxp.common.db.manyDatasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 基础多数据源切面类
 */
public class BaseManyDataSourceAspect {

    private static Logger logger = LoggerFactory.getLogger(BaseManyDataSourceAspect.class);

    public void before(JoinPoint point) {
        DataSource dataSource = AnnotationUtils.findAnnotation(((MethodSignature) point.getSignature()).getMethod(), DataSource.class);
        if (dataSource != null) {
            ManyDataSourceSwitch.setDataSourceType(dataSource.value());
            return;
        }

        logger.warn(point.getSignature() + "not found DataSource annotation,datasource will use defaultTargetDataSource.");
    }


    public void after(JoinPoint point) {
        ManyDataSourceSwitch.clearDataSourceType();
    }
}