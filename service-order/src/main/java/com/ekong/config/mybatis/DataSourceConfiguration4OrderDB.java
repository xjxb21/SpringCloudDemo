package com.ekong.config.mybatis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置OrderDB数据源
 */
@Configuration
public class DataSourceConfiguration4OrderDB {

    /**
     * 此功能在spring boot 的1.4.0前 dataSourceType 好像没有支持
     */
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.orderDBMaster")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    //Slave OrderDB
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "druid.orderDBSlave")
    public DataSource slaveDataSource1(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
