package com.subhajit.SbJdbcTemplateMultiDB.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;
import com.subhajit.SbJdbcTemplateMultiDB.dao.TestDao;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;
	
	@Autowired
	@Qualifier("platformTransactionManagerOracle")
	PlatformTransactionManager platformTransactionManagerOracle;
	
	@Autowired
	@Qualifier("platformTransactionManagerMysql")
	PlatformTransactionManager platformTransactionManagerMysql;

	
	
	public List<Student> fetchAllOracle() throws SQLException {
		return testDao.fetchAllOracle();
	}
	
	public List<Student> fetchAllMySQL() throws SQLException {
		return testDao.fetchAllMySQL();
	}
	
	@Transactional("platformTransactionManagerOracle")
	public int oraTwoTablesInsertAutoTrans(){	
		testDao.insert1stRecordOra();
		testDao.insert2ndRecordOra();	
		return 1;
	}
	
	public int oraTwoTablesInsertManualTrans() {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = platformTransactionManagerOracle.getTransaction(def);
		try {
			testDao.insert1stRecordOra();
			testDao.insert2ndRecordOra();
			platformTransactionManagerOracle.commit(status);
		} catch (Exception e) {
			platformTransactionManagerOracle.rollback(status);
			System.err.println(e.getMessage());
		}
		return 1;
	}
	
	
	@Transactional("platformTransactionManagerMysql")
	public int mysqlTwoTablesInsertAutoTrans() {	
		testDao.insert1stRecordMySQL();
		testDao.insert2ndRecordMySQL();	
		return 1;
	}
	
	public int mysqlTwoTablesInsertManualTrans() {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = platformTransactionManagerMysql.getTransaction(def);
		try {
			testDao.insert1stRecordMySQL();
			testDao.insert2ndRecordMySQL();
			platformTransactionManagerMysql.commit(status);
		} catch (Exception e) {
			platformTransactionManagerMysql.rollback(status);
			System.err.println(e.getMessage());
		}
		return 1;
	}
	
}
