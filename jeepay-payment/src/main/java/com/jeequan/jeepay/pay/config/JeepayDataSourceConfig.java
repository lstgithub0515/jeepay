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
@MapperScan(basePackages = "com.jeequan.jeepay.service.mapper", sqlSessionTemplateRef  = "jeepaySqlSessionTemplate")
public class JeepayDataSourceConfig {
    // @Bean
    // @Primary
    // @ConfigurationProperties(prefix = "spring.datasource.jeepay")//重点3 这里对应yml的当前数据源的前缀
    // public DataSource dataSource() {
    //     return DataSourceBuilder.create().build();
    //
    // }

    @Bean(name = "jeepaySqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("jeepayDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/jeequan/jeepay/service/mapper/**/*Mapper.xml"));//重点2 指定包扫描,当前这个数据源对应的mapper.xml文件在哪个resource下的包里，这里路径就指定哪里
        // bean.setPlugins(new Interceptor[] {xxx}); // 设置MyBatis插件
        return bean.getObject();
    }


    @Bean(name = "jeepayTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("jeepayDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "jeepaySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("jeepaySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
