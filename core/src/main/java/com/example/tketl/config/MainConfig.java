package com.example.tketl.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class MainConfig {
    @Bean
    @Primary
    public DataSource maindatasource() {

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url("jdbc:sqlserver://sqlserver:1433;encrypt=true;trustServerCertificate=true;databaseName=etl")
                .username("sa")
                .password("KsiazeHarry321!!")
                .build();

        return dataSource;
    }



}
