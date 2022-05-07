package com.subhajit.SbJdbcTemplateMultiDB.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;
import com.subhajit.SbJdbcTemplateMultiDB.service.TransactionService;


@RestController
public class TransactionController {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionController.class);

	@Autowired
	private TransactionService service;
	
	@GetMapping("/two-tables-insert-trans-anno-default")
	public String twoTablesInsertTransAnnoDefault(){
		return service.twoTablesInsertTransAnnoDefault();
	}
	
	@GetMapping("/one-table-insert-trans-anno-readonly")
	public String oneTableInsertTransAnnoReadonly() throws Exception {
		return service.oneTableInsertTransAnnoReadonly();
	}
	
	@GetMapping("/two-tables-insert-trans-anno-rollback")
	public String twoTablesInsertTransAnnoRollback() throws Exception{
		return service.twoTablesInsertTransAnnoRollback();
	}
	
	@GetMapping("/two-tables-insert-trans-anno")
	public String twoTablesInsertTransAnno(){
		return service.twoTablesInsertTransAnnoOne();
	}

	@GetMapping("/one-table-insert-and-read")
	public List<Student> oneTableInsertAndRead(){
		return service.oneTableInsertAndRead();
	}
	
	@GetMapping("/read-from-student")
	public List<Student> readFromStudent(){
		return service.readFromStudent();
	}
}
