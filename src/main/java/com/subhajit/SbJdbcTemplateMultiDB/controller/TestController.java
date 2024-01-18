package com.subhajit.SbJdbcTemplateMultiDB.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhajit.SbJdbcTemplateMultiDB.bean.ResponseObject;
import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;
import com.subhajit.SbJdbcTemplateMultiDB.service.TestService;

import javax.sql.DataSource;


@RestController
public class TestController {
	
	private static final Logger LOGGER = LogManager.getLogger(TestController.class);
	
	@Autowired
	private TestService service;

	@GetMapping("/oracle-fetch")
	public ResponseObject oracleFetch() throws IOException, SQLException {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setDatabase("Oracle");
		responseObject.setData(service.oracleFetch());
		return responseObject;
	}


	@GetMapping("/fetch-from-oracle")
	public ResponseObject fetchFromOracle(String flag) throws IOException, SQLException {
		System.out.println("In testConnectionPool1 TestController, thread-id starts : " + Thread.currentThread().getId());

		ResponseObject responseObject = new ResponseObject();
		responseObject.setDatabase("Oracle");
		responseObject.setData(service.fetchAllOracle(flag));
		return responseObject;
	}
	
	/*
	 * @GetMapping("/fetch-from-mysql") public ResponseObject fetchFromMysql()
	 * throws IOException, SQLException { ResponseObject responseObject = new
	 * ResponseObject(); responseObject.setDatabase("MySQL");
	 * responseObject.setData(service.fetchAllMySQL()); return responseObject; }
	 * 
	 * @GetMapping("/ora-two-tables-insert-auto-trans") public int
	 * oraTwoTablesInsertAutoTrans() throws Exception { return
	 * service.oraTwoTablesInsertAutoTrans(); }
	 * 
	 * @GetMapping("/ora-two-tables-insert-manual-trans") public int
	 * oraTwoTablesInsertmanualTrans() { return
	 * service.oraTwoTablesInsertManualTrans(); }
	 * 
	 * @GetMapping("/mysql-two-tables-insert-auto-trans") public int
	 * mysqlTwoTablesInsertAutoTrans() throws IOException, SQLException { return
	 * service.mysqlTwoTablesInsertAutoTrans(); }
	 * 
	 * @GetMapping("/mysql-two-tables-insert-manual-trans") public int
	 * mysqlTwoTablesInsertManualTrans() throws IOException, SQLException { return
	 * service.mysqlTwoTablesInsertManualTrans(); }
	 */
}
