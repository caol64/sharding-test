package com.demo.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration; // 5.2.0
//import org.apache.shardingsphere.infra.config.RuleConfiguration; // 5.1.2
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

class ShardingDatasourceTest {

    @Test
    void test() throws SQLException {
        Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();
        DruidDataSource druidDataSource = DruidDataSourceBuilder.build();
        dataSourceMap.put(druidDataSource.getName(), druidDataSource);
        Collection<RuleConfiguration> ruleConfigs = Collections.emptyList();
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(druidDataSource.getName(), dataSourceMap, ruleConfigs, null);
        dataSource.getConnection();
    }
}
