package com.subhajit.SbJdbcTemplateMultiDB.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;
import com.subhajit.SbJdbcTemplateMultiDB.dao.TransactionDao;


//@Service
public class TransactionService {
	
	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	@Qualifier("platformTransactionManagerOracle")
	PlatformTransactionManager platformTransactionManagerOracle;
	
	// Default behavior of @Transactional
	// If checked exception is thrown from DAO, rollback will not happen
	// If unchecked exception is thrown from DAO, rollback will happen
	// But for any exception(checked/unchecked) if happens during runtime, rollback will work
	@Transactional("platformTransactionManagerOracle")
	public String twoTablesInsertTransAnnoDefault(){	
		transactionDao.insert1stRecordOra();
		transactionDao.insert2ndRecordOra();
		return "Success!!";
	}
	
	
	// The fact is that we can't be sure that an insert or update won't occur when the readOnly flag is set. 
	// This behavior is vendor dependent, whereas JPA is vendor agnostic.
	@Transactional(value = "platformTransactionManagerOracle", readOnly = true)
	public String oneTableInsertTransAnnoReadonly(){	
		transactionDao.insert1stRecordOra();
		return "Success!!";
	}
	
	@Transactional(value = "platformTransactionManagerOracle", rollbackFor = { Exception.class })
	public String twoTablesInsertTransAnnoRollback() throws Exception{	
		transactionDao.insert1stRecordOra();
		transactionDao.insert2ndRecordOra();
		throw new SQLException("Exception!!");
		//return "Success!!";
	}
	
	
	public String twoTablesInsertTransAnnoOne() {
		return twoTablesInsertTransAnnoTwo();
	}
	
	private String twoTablesInsertTransAnnoTwo() {
		return twoTablesInsertTransAnnoThree();
	}
	
	@Transactional("platformTransactionManagerOracle")
	private String twoTablesInsertTransAnnoThree(){	
		transactionDao.insert1stRecordOra();
		transactionDao.insert2ndRecordOra();
		return "Success!!";
	}
	
	@Transactional(value = "platformTransactionManagerOracle")
	public List<Student> oneTableInsertAndRead(){	
		
		transactionDao.insertStudentOra();
		List<Student> studentList = transactionDao.fetchAllOracle();
		System.out.println("In service : " + studentList);
		try {
			Thread.sleep(30000);
		}catch(Exception e) {
		}
		
		return studentList;
	}
	
	@Transactional("platformTransactionManagerOracle")
	public List<Student> readFromStudent(){	
		List<Student> studentList = transactionDao.fetchAllOracle();
		System.out.println("In service : " + studentList);
		return studentList;
	}
}
