package com.yxp.common.db.plugin.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举类型处理类
 * Created by Xs on 2017/12/14.
 */
public class CodeEnumTypeHandler<E extends Enum<?> & BaseCodeEnum> extends BaseTypeHandler<BaseCodeEnum> {
    private static final Logger logger = LoggerFactory.getLogger(CodeEnumTypeHandler.class);

    private Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            logger.error("初始化 CodeEnumTypeHandler 异常........ class type is null！");
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        logger.info("初始化 CodeEnumTypeHandler ........" + type);
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseCodeEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        rs.getInt(columnName);
        return rs.wasNull() ? null : getEnum(rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        rs.getInt(columnIndex);
        return rs.wasNull() ? null : getEnum(rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        cs.getInt(columnIndex);
        return cs.wasNull() ? null : getEnum(cs.getInt(columnIndex));
    }

    private E getEnum(int code) {
        try {
            E[] enumConstants = type.getEnumConstants();
            for (E e : enumConstants) {
                if (e.getCode() == code) {
                    return e;
                }
            }
            logger.info("no match Enum .... ");
            return null;
        } catch (Exception ex) {
            logger.error("getEnumByCode Exception: " + ex.getMessage(), ex);
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
        }
    }

}
