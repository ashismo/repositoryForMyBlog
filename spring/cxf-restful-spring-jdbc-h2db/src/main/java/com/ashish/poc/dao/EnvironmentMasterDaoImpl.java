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

import com.ashish.poc.model.EnvironmentMaster;
import com.ashish.poc.model.Role;
import com.ashish.poc.model.Users;
import com.ashish.poc.util.CommonUtil;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Repository
public class EnvironmentMasterDaoImpl {

	private static final Logger log = Logger.getLogger(EnvironmentMasterDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public EnvironmentMaster findByEnvName(String name) throws Exception {
		EnvironmentMaster envMaster = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("env_name", name.toLowerCase());

			String sql = "SELECT * FROM ENVIRONMENT_MASTER WHERE lower(env_name)=:env_name";

			envMaster = namedParameterJdbcTemplate.queryForObject(sql, params,
					new EnvironmentMapper());

		} catch (Exception e) {
			log.error("No records found for env name: " + name, e);
		}
		return envMaster;

	}
	
	public EnvironmentMaster findByEnvId(int envId) throws Exception {
		EnvironmentMaster result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("env_id", envId);

			String sql = "SELECT * FROM ENVIRONMENT_MASTER WHERE env_id=:env_id";

			result = namedParameterJdbcTemplate.queryForObject(sql, params, new EnvironmentMapper());

		} catch (Exception e) {
			log.error("No records found for env id: " + envId, e);
		}
		return result;

	}

	public List<EnvironmentMaster> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM ENVIRONMENT_MASTER";

		List<EnvironmentMaster> result = namedParameterJdbcTemplate.query(sql, params,
				new EnvironmentMapper());

		return result;

	}

	/**
	 * This method creates environment into database
	 * @param envMaster
	 * @throws Exception 
	 */
	public void createEnvironment(EnvironmentMaster envMaster) throws Exception {
		try {
			if (envMaster != null && envMaster.getEnvId() == 0) {
				log.debug("Going to add " + envMaster);
				String sql = "INSERT INTO ENVIRONMENT_MASTER (env_name, env_desc, create_user, create_date) "
						+ " VALUES (:env_name, :env_desc, :create_user, :create_date);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("env_id", envMaster.getEnvId());
				parameters.put("env_name", envMaster.getEnvName());
				parameters.put("env_desc", envMaster.getEnvDesc());
				parameters.put("create_user", envMaster.getCreateUser());
				parameters.put("create_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(envMaster.getEnvName() + " not added");
			}
		} catch (Exception e) {
			log.error("Unable to create module for modulename: " + envMaster.getEnvName(), e);
			throw e;
		}
	}
	
	/**
	 * This method updates environment into database
	 * @param envMaster
	 * @throws Exception 
	 */
	public void updateEnvironment(EnvironmentMaster envMaster) throws Exception {
		try {
			if (envMaster != null && envMaster.getEnvId() > 0) {
				log.debug("Going to update " + envMaster.getEnvName());
				String sql = "update ENVIRONMENT_MASTER set env_desc=:env_desc, "
						+ "update_user=:update_user, update_date=:update_date where env_id=:env_id";
				
//				String sql = "update USERS set name='a' where user_id=1";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("env_id", envMaster.getEnvId());
				parameters.put("env_name", envMaster.getEnvName());
				parameters.put("env_desc", envMaster.getEnvDesc());
				parameters.put("update_user", envMaster.getUpdateUser());
				parameters.put("update_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(envMaster.getEnvName() + " Environement not updated");
			}
		} catch (Exception e) {
			log.error("Unable to update environment for env_name: " + envMaster.getEnvName(), e);
			throw e;
		}
	}
	
	private static final class EnvironmentMapper implements RowMapper<EnvironmentMaster> {

		public EnvironmentMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
			EnvironmentMaster envMaster = new EnvironmentMaster();
			if(rs.getInt("env_id") > 0) envMaster.setEnvId(rs.getInt("env_id"));
			envMaster.setEnvName(rs.getString("env_name"));
			envMaster.setEnvDesc(rs.getString("env_desc"));
			envMaster.setCreateUser(rs.getString("create_user"));
			envMaster.setUpdateUser(rs.getString("update_user"));
			envMaster.setCreateDate(rs.getTimestamp("create_date"));
			envMaster.setUpdateDate(rs.getTimestamp("update_date"));
			return envMaster;
		}
	}

}
