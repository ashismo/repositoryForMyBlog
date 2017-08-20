package com.ashish.learning.v4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringJDBCDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UserBean getUserByUserName(String username) {
		String sql = "SELECT * FROM USERS WHERE username=:username";
		UserBean ub = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", username);
			ub = jdbcTemplate.queryForObject(sql, params, new UserBeanMapper());

		} catch (Exception e) {
			System.out.println("No records found for user id: " + username);
			e.printStackTrace();
		}
		return ub;
	}
	
	public void createUser(UserBean ub) {
		try {
				String sql = "INSERT INTO USERS (username,name,password,role_id) "
						+ " VALUES (:username, :name, :password, :roleId);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("username", ub.getUsername());
				parameters.put("name", ub.getName());
				parameters.put("password", ub.getPassword());
				parameters.put("roleId", ub.getRoleId());

				jdbcTemplate.update(sql, parameters);
		} catch (Exception e) {
			System.out.println("Unable to create username: " + ub.getUsername());
			e.printStackTrace();
		}
	}
	
	private static final class UserBeanMapper implements RowMapper<UserBean> {

		@Override
		public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserBean userBean = new UserBean();
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				return userBean;
		}
		
	}
}
