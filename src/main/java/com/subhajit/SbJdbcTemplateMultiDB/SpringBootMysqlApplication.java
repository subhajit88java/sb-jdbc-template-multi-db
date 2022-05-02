package com.subhajit.SbJdbcTemplateMultiDB;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class SpringBootMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMysqlApplication.class, args);
	}

	// STEP : 1, Creation of Oracle Datasource
	@Bean(name = "oracledatasource")
	@ConfigurationProperties(prefix = "oracle.datasource")
	public DataSource oracledatasource(){
		DataSource oracleDatasource = DataSourceBuilder.create().build();
		return oracleDatasource;
	}
	
	// STEP : 1, Creation of MySQL Datasource
	@Bean(name = "mysqldatasource")
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource mysqldatasource() throws SQLException{
		DataSource mysqlDatasource = DataSourceBuilder.create().build();	
		return mysqlDatasource;
	}
	
	// STEP : 2, Creation of JDBC Template for Oracle
	@Bean(name = "jdbcTemplateOracle")
	public JdbcTemplate jdbcTemplateOracle(@Autowired @Qualifier("oracledatasource") DataSource oracleDatasource) {
		JdbcTemplate jdbcTemplateOracle = new JdbcTemplate();
		jdbcTemplateOracle.setDataSource(oracleDatasource);
		return jdbcTemplateOracle;
	}
	
	// STEP : 2, Creation of JDBC Template for MySQL
	@Bean(name = "jdbcTemplateMySQL")
	public JdbcTemplate jdbcTemplateMySQL(@Autowired @Qualifier("mysqldatasource") DataSource mysqlDatasource) throws SQLException {
		JdbcTemplate jdbcTemplateMySQL = new JdbcTemplate();
		jdbcTemplateMySQL.setDataSource(mysqlDatasource);
		return jdbcTemplateMySQL;
	}

	// STEP : 3, Creation of Transaction manager for Oracle
	@Bean(name = "platformTransactionManagerOracle")
	public PlatformTransactionManager platformTransactionManagerOracle(@Autowired @Qualifier("oracledatasource") DataSource oracleDatasource) {
	
		DataSourceTransactionManager platformTransactionManagerOracle =new DataSourceTransactionManager();
		platformTransactionManagerOracle.setDataSource(oracleDatasource);		
		return platformTransactionManagerOracle;
		
	}
	
	// STEP : 3, Creation of Transaction manager for MySQL
	@Bean(name = "platformTransactionManagerMysql")
	public PlatformTransactionManager platformTransactionManagerMysql(@Autowired @Qualifier("mysqldatasource") DataSource mysqlDatasource) {
		
		DataSourceTransactionManager platformTransactionManagerMysql =new DataSourceTransactionManager();
		platformTransactionManagerMysql.setDataSource(mysqlDatasource);	
		return platformTransactionManagerMysql;
		
	}
}

/* 
 * This project explains multiple Database connections through Spring JDBC template 
 * Databases : Oracle and MySQL 
 * 
*/
