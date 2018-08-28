package com.stylefeng.guns.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.datascope.DataScopeInterceptor;
import com.stylefeng.guns.core.datasource.DruidProperties;
import com.stylefeng.guns.core.mutidatasource.DynamicDataSource;
import com.stylefeng.guns.core.mutidatasource.config.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * MybatisPlus配置
 *
 * @author tgshi
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = {"com.stylefeng.guns.modular.*.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    @Autowired
    LocalBdpDataSourceProperties localBdpDataSourceProperties;

    @Autowired
    DevDataSourceProperties devDataSourceProperties;

    @Autowired
    ProdDataSourceProperties prodDataSourceProperties;

    @Autowired
    ProdLogCheckDataSourceProperties prodLogCheckDataSourceProperties;
    @Autowired
    TestDataSourceProperties testDataSourceProperties;

    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源 localBdpDataSource
     */
    private DruidDataSource localBdpDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        localBdpDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源 DevDataSource
     */
    private DruidDataSource devDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        devDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源 ProdDataSource
     */
    private DruidDataSource prodDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        prodDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源 prodLogCheckDataSource
     */
    private DruidDataSource prodLogCheckDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        prodLogCheckDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源 testDataSource
     */
    private DruidDataSource testDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        testDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceGuns() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceGuns();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource dataSourceGuns = dataSourceGuns();
        DruidDataSource bizDataSource = bizDataSource();
        DruidDataSource localBdpDataSource = localBdpDataSource();
        DruidDataSource devDataSource = devDataSource();
        DruidDataSource prodDataSource = prodDataSource();
        DruidDataSource prodLogchekDataSource = prodLogCheckDataSource();
        DruidDataSource testDataSource = testDataSource();



        try {
            dataSourceGuns.init();
            bizDataSource.init();
            // localBdpDataSource.init();
            devDataSource.init();
            prodDataSource.init();
            prodLogchekDataSource.init();
            testDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(DatasourceEnum.DATA_SOURCE_GUNS, dataSourceGuns);
        hashMap.put(DatasourceEnum.DATA_SOURCE_BIZ, bizDataSource);
        hashMap.put(DatasourceEnum.LOCA_BDP_DATA_SOURCE, localBdpDataSource);
        hashMap.put(DatasourceEnum.DEV_DATA_SOURCE, devDataSource);
        hashMap.put(DatasourceEnum.TEST_DATA_SOURCE, testDataSource);
        hashMap.put(DatasourceEnum.PROD_DATA_SOURCE, prodDataSource);
        hashMap.put(DatasourceEnum.PROD_LOG_CHECK_DATA_SOURCE, prodLogchekDataSource);

        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
