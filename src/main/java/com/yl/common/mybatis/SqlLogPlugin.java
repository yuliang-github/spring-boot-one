package com.yl.common.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author Alex
 * @since 2018/11/5 10:07
 */
@Intercepts({
//    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlLogPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler target = (StatementHandler) invocation.getTarget();
//        String sql = target.getBoundSql().getSql();
//        System.err.println("mybatis sql:" + sql);

        Object[] args = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) args[0];

        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);

        System.err.println("mybatis sql:" + boundSql.getSql() + ", param:" + JSONObject.toJSONString(args[1]));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
