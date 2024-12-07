package com.example.batch.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.h2metadata")
  DataSourceProperties h2Properties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.mysqlmain")
  DataSourceProperties mysqlProperties() {
    return new DataSourceProperties();
  }

  @BatchDataSource
  @Bean
  DataSource h2DataSource() {
    return h2Properties().initializeDataSourceBuilder().build();
  }

  @Primary
  @Bean
  DataSource mysqlDataSource() {
    return mysqlProperties().initializeDataSourceBuilder().build();
  }
}
