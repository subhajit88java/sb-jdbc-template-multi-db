package com.subhajit.SbJdbcTemplateMultiDB.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import com.subhajit.SbJdbcTemplateMultiDB.bean.Student;


@Repository
public class TestDao {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("jdbcTemplateOracle") private JdbcTemplate jdbcTemplateOracle;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("jdbcTemplateMySQL") private JdbcTemplate jdbcTemplateMySQL;
	 */
	
//	@Autowired
//	@Qualifier("jdbcTemplateTest")
	private JdbcTemplate jdbcTemplateTest;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Student> oracleFetch() throws SQLException {

		String sql = "SELECT STUDENT_ID, STUDENT_NAME FROM STUDENT";

		List<Student> studentList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Student> studentList = new ArrayList<>();
				while(rs.next()) {
					studentList.add(new Student(rs.getString(1),rs.getString(2),null));
				}
				return studentList;
			}
		});

		return studentList;
	}
	
	public List<Student> fetchAllOracle(String flag) throws SQLException {
		
		String sql = "SELECT ID, NAME, MARKS FROM STUDENT";
		
		List<Student> studentList = jdbcTemplateTest.query(sql, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Student> studentList = new ArrayList<>();
				System.out.println("1 calling : " + flag + " - " + Thread.currentThread().getId());
				while(rs.next()) {
					studentList.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3)));
				}
				return studentList;
			}
		});
		
		return studentList;
	}
	
public List<Student> fetchAllOracle1(String flag) throws SQLException {
		
		String sql = "SELECT ID, NAME, MARKS FROM STUDENT";
		
		
		List<Student> studentList = jdbcTemplateTest.query(sql, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Student> studentList = new ArrayList<>();
				System.out.println("2 calling : " + flag + " - " + Thread.currentThread().getId());
				while(rs.next()) {
					studentList.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3)));
				}
				return studentList;
			}
		});
		
		return studentList;
	}

public List<Student> fetchAllOracle2(String flag) throws SQLException {
	
	String sql = "SELECT ID, NAME, MARKS FROM STUDENT";
	
	
	List<Student> studentList = jdbcTemplateTest.query(sql, new ResultSetExtractor<List<Student>>() {

		@Override
		public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Student> studentList = new ArrayList<>();
			System.out.println("3 calling : " + flag);
			try {
				Thread.sleep(10000);
			}catch(Exception e) {}
			while(rs.next()) {
				studentList.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3)));
			}
			return studentList;
		}
	});
	
	return studentList;
}
	/*
	 * public List<Student> fetchAllMySQL() throws SQLException {
	 * 
	 * String sql = "SELECT ID, NAME, MARKS FROM STUDENT";
	 * 
	 * List<Student> studentList = jdbcTemplateMySQL.query(sql, new
	 * ResultSetExtractor<List<Student>>() {
	 * 
	 * @Override public List<Student> extractData(ResultSet rs) throws SQLException,
	 * DataAccessException { List<Student> studentList = new ArrayList<>();
	 * while(rs.next()) { studentList.add(new
	 * Student(rs.getString(1),rs.getString(2),rs.getString(3))); } return
	 * studentList; } });
	 * 
	 * return studentList; }
	 * 
	 * public int insert1stRecordOra() {
	 * 
	 * String insert = "INSERT INTO TABLE1(ID, NAME) VALUES(?,?)";
	 * 
	 * int status = jdbcTemplateOracle.update(insert, new PreparedStatementSetter()
	 * {
	 * 
	 * @Override public void setValues(PreparedStatement ps) throws SQLException {
	 * 
	 * ps.setString(1, "1"); ps.setString(2, "Subhajit");
	 * 
	 * }
	 * 
	 * });
	 * 
	 * return status; }
	 * 
	 * public int insert2ndRecordOra(){
	 * 
	 * String insert = "INSERT INTO TABLE2(ID, CITY) VALUES(?,?)";
	 * 
	 * int status = jdbcTemplateOracle.update(insert, new PreparedStatementSetter()
	 * {
	 * 
	 * @Override public void setValues(PreparedStatement ps) throws SQLException {
	 * 
	 * ps.setString(1, "1"); ps.setString(2, "Durgapur");
	 * 
	 * }
	 * 
	 * });
	 * 
	 * throw new RuntimeException("testing run");
	 * 
	 * 
	 * }
	 * 
	 * public int insert1stRecordMySQL(){
	 * 
	 * String insert = "INSERT INTO TABLE1(ID, NAME) VALUES(?,?)";
	 * 
	 * int status = jdbcTemplateMySQL.update(insert, new PreparedStatementSetter() {
	 * 
	 * @Override public void setValues(PreparedStatement ps) throws SQLException {
	 * 
	 * ps.setString(1, "1"); ps.setString(2, "Subhajit");
	 * 
	 * }
	 * 
	 * });
	 * 
	 * return status; }
	 * 
	 * public int insert2ndRecordMySQL() {
	 * 
	 * String insert = "INSERT INTO TABLE2(ID, CITY) VALUES(?,?)";
	 * 
	 * int status = jdbcTemplateMySQL.update(insert, new PreparedStatementSetter() {
	 * 
	 * @Override public void setValues(PreparedStatement ps) throws SQLException {
	 * 
	 * ps.setString(1, "1"); ps.setString(2, "Durgapur");
	 * 
	 * }
	 * 
	 * });
	 * 
	 * return status; }
	 */
}
