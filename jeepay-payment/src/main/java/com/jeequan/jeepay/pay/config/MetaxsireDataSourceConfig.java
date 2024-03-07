package com.jeequan.jeepay.pay.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: lst
 * @Date: 2024/2/28
 **/
@Configuration
@MapperScan(basePackages = "com.jeequan.jeepay.service.mappermetaxsire", sqlSessionTemplateRef  = "metaxsireSqlSessionTemplate")
public class MetaxsireDataSourceConfig {
    // @Bean(name = "dataSourceSecond")
    // @ConfigurationProperties(prefix = "spring.datasource.metaxsire-mysql")//重点3 这里对应yml的当前数据源的前缀
    // public DataSource dataSource() {
    //     return DataSourceBuilder.create().build();
    //
    // }

    @Bean(name = "metaxsireSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("metaxsireDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/jeequan/jeepay/service/mappermetaxsire/**/*Mapper.xml"));//重点2 指定包扫描,当前这个数据源对应的mapper.xml文件在哪个resource下的包里，这里路径就指定哪里
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml"));//重点2 指定包扫描,当前这个数据源对应的mapper.xml文件在哪个resource下的包里，这里路径就指定哪里
        // bean.setPlugins(new Interceptor[] {xxx}); // 设置MyBatis插件
        return bean.getObject();
    }


    @Bean(name = "metaxsireTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("metaxsireDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "metaxsireSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("metaxsireSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
