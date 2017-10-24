package com.hubciti.generaluse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.hubciti.common.pojos.Temp;

public class GeneralUseDaoImpl implements GeneralUseDao {

	/*private static final Logger LOG = LoggerFactory.getLogger(GeneralUseDaoImpl.class);*/

	

	private JdbcTemplate jdbctemplate;

	private SimpleJdbcCall simpleJdbcCall;

	public void setDataSource(DataSource dataSource) {
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	public String temp() {
		String response = null;
		Temp temp = null;
		simpleJdbcCall = new SimpleJdbcCall(jdbctemplate);
		String SQL = "SELECT UserID, UserName FROM Users WHERE UserID=?";
		try {
			temp = jdbctemplate.queryForObject(SQL, new Object[] { 1 }, new RowMapper<Temp>() {

				public Temp mapRow(ResultSet rs, int row) throws SQLException {
					final Temp temp = new Temp();
					temp.setUserId(rs.getInt("UserID"));
					temp.setUserName(rs.getString("UserName"));
					return temp;
				}

			});
		} catch (EmptyResultDataAccessException edae) {
			return null;
		} catch (DataAccessException dae) {

		}

		response = "UserID is " + temp.getUserId() + ", UserName is " + temp.getUserName();
		return response;
	}

}
