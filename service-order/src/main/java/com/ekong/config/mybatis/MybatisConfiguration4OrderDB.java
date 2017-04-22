package com.ekong.config.mybatis;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiao on 2017/3/27.
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration4OrderDB.class})
public class MybatisConfiguration4OrderDB extends MybatisAutoConfiguration {

    public MybatisConfiguration4OrderDB(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider);
    }

//    /**
//     * 重写sqlSessionFactory方法，使sqlSessionFactory初始化指定roundRobinDataSourceProxy
//     *
//     * @param dataSource
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        return super.sqlSessionFactory(roundRobinDataSourceProxy());
//    }

    /**
     * 放弃重写方法，修改为Qualifier指定bean的方式
     */
    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "roundRobinDataSourceProxy") DataSource dataSource) throws Exception {
        SqlSessionFactory sqlSessionFactory = super.sqlSessionFactory(dataSource);
        return sqlSessionFactory;
    }

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name = "roundRobinDataSourceProxy")
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap();
        targetDataResources.put(DbContextHolder4OrderDB.DbType.MASTER, masterDataSource);
        targetDataResources.put(DbContextHolder4OrderDB.DbType.SLAVE, slaveDataSource);
        proxy.setDefaultTargetDataSource(masterDataSource);//默认源
        proxy.setTargetDataSources(targetDataResources);
        //需要显示调用afterPropertiesSet方法初始化AbstractRoutingDataSource
        proxy.afterPropertiesSet();
        return proxy;
    }
}
