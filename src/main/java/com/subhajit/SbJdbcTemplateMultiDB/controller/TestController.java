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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhajit.SbJdbcTemplateMultiDB.bean.ResponseObject;
import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;
import com.subhajit.SbJdbcTemplateMultiDB.service.TestService;


@RestController
public class TestController {
	
	private static final Logger LOGGER = LogManager.getLogger(TestController.class);
	
	@Autowired
	private TestService service;
	
	
	@GetMapping("/fetch-from-oracle")
	public ResponseObject fetchFromOracle() throws IOException, SQLException {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setDatabase("Oracle");
		responseObject.setData(service.fetchAllOracle());
		return responseObject;
	}
	
	@GetMapping("/fetch-from-mysql")
	public ResponseObject fetchFromMysql() throws IOException, SQLException {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setDatabase("MySQL");
		responseObject.setData(service.fetchAllMySQL());
		return responseObject;
	}
	
	@GetMapping("/ora-two-tables-insert-auto-trans")
	public int oraTwoTablesInsertAutoTrans() throws Exception {
		return service.oraTwoTablesInsertAutoTrans();
	}
	
	@GetMapping("/ora-two-tables-insert-manual-trans")
	public int oraTwoTablesInsertmanualTrans() {
		return service.oraTwoTablesInsertManualTrans();
	}

	@GetMapping("/mysql-two-tables-insert-auto-trans")
	public int mysqlTwoTablesInsertAutoTrans() throws IOException, SQLException {
		return service.mysqlTwoTablesInsertAutoTrans();
	}
	
	@GetMapping("/mysql-two-tables-insert-manual-trans")
	public int mysqlTwoTablesInsertManualTrans() throws IOException, SQLException {
		return service.mysqlTwoTablesInsertManualTrans();
	}
}
