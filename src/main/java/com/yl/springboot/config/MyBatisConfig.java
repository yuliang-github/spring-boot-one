package com.yl.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.yl.common.mybatis.SqlLogPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Alex
 * @since 2018/10/19 15:47
 */
@Configuration
@PropertySources(
    @PropertySource(value = {"classpath:config/datasource.properties"},encoding = "UTF-8")
)
@MapperScan(value = {"com.yl.common.mapper"})
@EnableTransactionManagement // 开启注解式事务驱动
public class MyBatisConfig {

    @Bean(value = "dataSource")
    @Primary // 优先使用
    public DataSource druidDataSource(
        @Value("${jdbc.driverClassName}")String driverClass,
        @Value("${jdbc.url}")String url,
        @Value("${jdbc.user}")String user,
        @Value("${jdbc.password}")String password
    ){
        DataSource dataSource = new DruidDataSource();
        ((DruidDataSource) dataSource).setDriverClassName(driverClass);
        ((DruidDataSource) dataSource).setUrl(url);
        ((DruidDataSource) dataSource).setUsername(user);
        ((DruidDataSource) dataSource).setPassword(password);
        ((DruidDataSource) dataSource).setInitialSize(100);// 初始化连接池大小
        ((DruidDataSource) dataSource).setMinIdle(50);// 最小连接数
        ((DruidDataSource) dataSource).setMaxActive(200);// 最大连接数
        ((DruidDataSource) dataSource).setMaxWait(1000*60);// 最大等待超时时间
        ((DruidDataSource) dataSource).setTimeBetweenEvictionRunsMillis(1000*60*2);// 检查并关闭空闲连接的间隔
        ((DruidDataSource) dataSource).setMinEvictableIdleTimeMillis(1000*60);// 连接在池中最小生存时间
        ((DruidDataSource) dataSource).setValidationQuery("SELECT 'x'");// 测试连接是否有效的sql
        ((DruidDataSource) dataSource).setTestOnBorrow(true);// 获取连接时,测试连接是否有效,防止获取到无效连接
        ((DruidDataSource) dataSource).setTestWhileIdle(true);// 获取连接时,如果连接空闲时间大于setTimeBetweenEvictionRunsMillis时,检测连接
        ((DruidDataSource) dataSource).setTestOnReturn(false);// 归还连接时,是否检测连接
        // 是否缓存preparedStatement,也就是PSCache。PSCache对支持游标的数据库性能提升巨大,比如说oracle。在mysql下建议关闭。
        ((DruidDataSource) dataSource).setPoolPreparedStatements(false);
        return dataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{ new SqlLogPlugin()});
        return sqlSessionFactoryBean;
    }


}
