package com.subhajit.SbJdbcTemplateMultiDB.bean;

import java.util.List;

public class ResponseObject {
	
	private String database;
	private List<Student> data;
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public List<Student> getData() {
		return data;
	}
	public void setData(List<Student> data) {
		this.data = data;
	}

}
