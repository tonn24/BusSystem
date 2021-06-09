package com.example.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Autowired
  private DataSource dataSource;

  @Bean
  public TransactionManager transactionManager(){
    return new DataSourceTransactionManager(dataSource);
  }



}
