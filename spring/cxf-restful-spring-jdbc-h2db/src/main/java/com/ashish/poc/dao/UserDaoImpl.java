package com.ashish.poc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashish.poc.model.Users;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Repository
public class UserDaoImpl {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private PasswordEncodeDecodeUtil passwordEncodeDecode;

	public Users findByUserName(String name) throws Exception {
		Users result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", name);

			String sql = "SELECT * FROM users WHERE username=:username";

			result = namedParameterJdbcTemplate.queryForObject(sql, params,
					new UserMapper());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}
	
	public Users findByUserId(int userId) throws Exception {
		Users result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);

			String sql = "SELECT * FROM users WHERE user_id=:userId";

			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());

		} catch (Exception e) {
			System.out.println("No records found for user id: " + userId);
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public List<Users> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM users";

		List<Users> result = namedParameterJdbcTemplate.query(sql, params,
				new UserMapper());

		return result;

	}

	/**
	 * This method creates user into database
	 * @param user
	 * @throws Exception 
	 */
	public void createUser(Users user) throws Exception {
		try {
			if (user != null && (user.getUserId() == null || user.getUserId() == 0)) {
				System.out.println("Going to add " + user);
				String sql = "INSERT INTO users (user_id, username, name, password, role_id, email, create_user, create_date) "
						+ " VALUES (:user_id, :username, :name, :password, :role_id, :email, :create_user, :create_date);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("user_id", user.getUserId());
				parameters.put("username", user.getUsername());
				parameters.put("name", user.getName());
				parameters.put("password",
						passwordEncodeDecode.base64encode(user.getPassword()));
				parameters.put("email", user.getEmail());
				parameters.put("role_id", user.getRoleId());
				parameters.put("create_user", user.getCreateUser());
				parameters.put("create_date", user.getCreateDate());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				System.out.println(user.getUsername() + " not added");
			}
		} catch (Exception e) {
			System.out.println("Unable to create user for user_name: " + user.getUsername());
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * This method updates user into database
	 * @param user
	 * @throws Exception 
	 */
	public void updateUser(Users user) throws Exception {
		try {
			if (user != null && user.getUserId() != null && user.getUserId() > 0) {
				System.out.println("Going to update " + user.getUsername());
				String sql = "update PUBLIC.USERS set name=:name, password=:password, role_id=:role_id, email=:email, "
						+ "update_user=:update_user, update_date=:update_date where user_id=:user_id";
				
//				String sql = "update USERS set name='a' where user_id=1";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("user_id", user.getUserId());
				parameters.put("name", user.getName());
				parameters.put("password",
						passwordEncodeDecode.base64encode(user.getPassword()));
				parameters.put("email", user.getEmail());
				parameters.put("role_id", user.getRoleId());
				parameters.put("update_user", user.getUpdateUser());
				parameters.put("update_date", user.getUpdateDate());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				System.out.println(user.getUsername() + " not updated");
			}
		} catch (Exception e) {
			System.out.println("Unable to update user for user_name: " + user.getUsername());
			e.printStackTrace();
			throw e;
		}
	}
	
	private static final class UserMapper implements RowMapper<Users> {

		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users user = new Users();
			user.setUserId(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			if(rs.getInt("role_id") > 0) user.setRoleId(rs.getInt("role_id"));
			user.setEmail(rs.getString("email"));
			user.setCreateUser(rs.getString("create_user"));
			user.setUpdateUser(rs.getString("update_user"));
			user.setCreateDate(rs.getTimestamp("create_date"));
			user.setUpdateDate(rs.getTimestamp("update_date"));
			return user;
		}
	}

}
