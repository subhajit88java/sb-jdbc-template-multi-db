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

//@Repository
public class TransactionDao {

	@Autowired
	@Qualifier("jdbcTemplateOracle")
	private JdbcTemplate jdbcTemplateOracle;

	public List<Student> fetchAllOracle() {

		String sql = "SELECT ID, NAME, MARKS FROM STUDENT";

		List<Student> studentList = jdbcTemplateOracle.query(sql, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Student> studentList = new ArrayList<>();
				while (rs.next()) {
					studentList.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3)));
				}
				return studentList;
			}
		});

		return studentList;
	}

	public int insertStudentOra() {

		String insert = "INSERT INTO STUDENT(ID, NAME, MARKS) VALUES(?,?,?)";

		int status = jdbcTemplateOracle.update(insert, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, "1");
				ps.setString(2, "Subhajit");
				ps.setString(3, "95");

			}

		});

		return status;
	}
	
	public int insert1stRecordOra() {

		String insert = "INSERT INTO TABLE1(ID, NAME) VALUES(?,?)";

		int status = jdbcTemplateOracle.update(insert, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, "1");
				ps.setString(2, "Subhajit");

			}

		});

		return status;
	}

	public int insert2ndRecordOra() {

		String insert = "INSERT INTO TABLE2(ID, CITY) VALUES(?,?)";

		int status = jdbcTemplateOracle.update(insert, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, "1");
				ps.setString(2, "Durgapur");

			}

		});

		return status;
	}
}
