package com.segfault.interceptor;


import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * 分页处理拦截器
 *
 * @Intercepts 声明是拦截器
 * @Signature 签名配置
 * type:
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {


    private Integer pageSize;
    private String dbType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //获得StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //StatementHandler包装类
        MetaObject metaObjectHandler = SystemMetaObject.forObject(statementHandler);

        while (metaObjectHandler.hasGetter("h")) {
            Object obj = metaObjectHandler.getValue("h");
            metaObjectHandler = SystemMetaObject.forObject(obj);
        }
        while (metaObjectHandler.hasGetter("target")) {
            Object obj = metaObjectHandler.getValue("target");
            metaObjectHandler = SystemMetaObject.forObject(obj);
        }

        //获取查询接口映射的相关信息
        MappedStatement mappedStatement =
                (MappedStatement) metaObjectHandler.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();

        if (mapId.matches(".+ByPageParam$")) {
            ParameterHandler parameterHandler =
                    (ParameterHandler) metaObjectHandler.getValue("delegate.parameterHandler");
            Map param = (Map) parameterHandler.getParameterObject();
            int start = (Integer) param.get("start");
            int limit = (Integer) param.get("limit");
            String sql = ((String) metaObjectHandler.getValue("delegate.boundSql.sql")).trim();
            sql = sql + " limit " + start + "," + limit;
            metaObjectHandler.setValue("delegate.boundSql.sql", sql);
        }

        return invocation.proceed();
    }

    /**
     * 获取代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String limit1 = properties.getProperty("limit", "10");
        this.pageSize = Integer.valueOf(limit1);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
