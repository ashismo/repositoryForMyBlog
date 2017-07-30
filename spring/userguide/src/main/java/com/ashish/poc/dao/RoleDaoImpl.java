package com.ashish.poc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashish.poc.model.Role;
import com.ashish.poc.model.Users;
import com.ashish.poc.util.CommonUtil;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Repository
public class RoleDaoImpl {

	private static final Logger log = Logger.getLogger(RoleDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public Role findByRoleName(String name) throws Exception {
		Role role = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("rolename", name.toLowerCase());

			String sql = "SELECT * FROM role WHERE lower(rolename)=:rolename";

			role = namedParameterJdbcTemplate.queryForObject(sql, params,
					new RoleMapper());

		} catch (Exception e) {
			log.error("No records found for role name: " + name, e);
		}
		return role;

	}
	
	public Role findByRoleId(int roleId) throws Exception {
		Role result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("role_id", roleId);

			String sql = "SELECT * FROM role WHERE role_id=:role_id";

			result = namedParameterJdbcTemplate.queryForObject(sql, params, new RoleMapper());

		} catch (Exception e) {
			log.error("No records found for user id: " + roleId, e);
		}
		return result;

	}

	public List<Role> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM role";

		List<Role> result = namedParameterJdbcTemplate.query(sql, params,
				new RoleMapper());

		return result;

	}

	/**
	 * This method creates user into database
	 * @param role
	 * @throws Exception 
	 */
	public void createRole(Role role) throws Exception {
		try {
			if (role != null && role.getRoleId() == 0) {
				log.debug("Going to add " + role);
				String sql = "INSERT INTO role (rolename, role_desc, create_user, create_date) "
						+ " VALUES (:rolename, :role_desc, :create_user, :create_date);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("role_id", role.getRoleId());
				parameters.put("rolename", role.getRoleName());
				parameters.put("role_desc", role.getRoleDesc());
				parameters.put("create_user", commonUtil.getLoggedInUsername());
				parameters.put("create_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(role.getRoleName() + " not added");
			}
		} catch (Exception e) {
			log.error("Unable to create role for rolename: " + role.getRoleName(), e);
			throw e;
		}
	}
	
	/**
	 * This method updates user into database
	 * @param role
	 * @throws Exception 
	 */
	public void updateRole(Role role) throws Exception {
		try {
			if (role != null && role.getRoleId() > 0) {
				log.debug("Going to update " + role.getRoleName());
				String sql = "update PUBLIC.ROLE set rolename=:role_name, role_desc=:role_desc, "
						+ "update_user=:update_user, update_date=:update_date where role_id=:role_id";
				
//				String sql = "update USERS set name='a' where user_id=1";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("role_id", role.getRoleId());
				parameters.put("role_name", role.getRoleName());
				parameters.put("role_desc", role.getRoleDesc());
				parameters.put("update_user", commonUtil.getLoggedInUsername());
				parameters.put("update_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(role.getRoleName() + " Role not updated");
			}
		} catch (Exception e) {
			log.error("Unable to update user for role_name: " + role.getRoleName(), e);
			throw e;
		}
	}
	
	private static final class RoleMapper implements RowMapper<Role> {

		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			if(rs.getInt("role_id") > 0) role.setRoleId(rs.getInt("role_id"));
			role.setRoleName(rs.getString("rolename"));
			role.setRoleDesc(rs.getString("role_desc"));
			role.setCreateUser(rs.getString("create_user"));
			role.setUpdateUser(rs.getString("update_user"));
			role.setCreateDate(rs.getTimestamp("create_date"));
			role.setUpdateDate(rs.getTimestamp("update_date"));
			return role;
		}
	}

}