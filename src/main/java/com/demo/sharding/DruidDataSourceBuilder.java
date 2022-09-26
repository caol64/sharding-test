package com.demo.sharding;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import java.util.ArrayList;
import java.util.List;

public class DruidDataSourceBuilder {

    public static DruidDataSource build() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.h2.Driver");
        druidDataSource.setUrl("jdbc:h2:mem:test0;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setName("ds0");
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(3);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setUseUnfairLock(true);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setMaxEvictableIdleTimeMillis(300000);
        druidDataSource.setKeepAlive(false);
        druidDataSource.setProxyFilters(buildFilters());
        return druidDataSource;
    }

    private static List<Filter> buildFilters() {
        List<Filter> filters = new ArrayList<>();
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        filters.add(statFilter);

        LogFilter logFilter = new Slf4jLogFilter();
        logFilter.setConnectionLogErrorEnabled(true);
        logFilter.setStatementLogErrorEnabled(true);
        logFilter.setStatementExecutableSqlLogEnable(true);
        filters.add(logFilter);
        return filters;
    }
}
