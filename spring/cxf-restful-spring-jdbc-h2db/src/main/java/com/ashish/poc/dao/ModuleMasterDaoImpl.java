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

import com.ashish.poc.model.Module;
import com.ashish.poc.model.ModuleEnvironmentMaster;
import com.ashish.poc.util.CommonUtil;

@Repository
public class ModuleMasterDaoImpl {

	private static final Logger log = Logger.getLogger(ModuleMasterDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public Module findByModuleName(String name) throws Exception {
		Module envMaster = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("module_name", name.toLowerCase());

			String sql = "SELECT * FROM MODULE WHERE lower(module_name)=:module_name";

			envMaster = namedParameterJdbcTemplate.queryForObject(sql, params,
					new ModuleMapper());

		} catch (Exception e) {
			log.error("No records found for module name: " + name, e);
		}
		return envMaster;

	}
	
	public Module findByModuleId(int moduleId) throws Exception {
		Module result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("module_id", moduleId);

			String sql = "SELECT * FROM MODULE WHERE module_id=:module_id";

			result = namedParameterJdbcTemplate.queryForObject(sql, params, new ModuleMapper());

		} catch (Exception e) {
			log.error("No records found for module id: " + moduleId, e);
		}
		return result;

	}

	public List<Module> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM MODULE";

		List<Module> result = namedParameterJdbcTemplate.query(sql, params,
				new ModuleMapper());

		return result;

	}

	/**
	 * This method creates module into database
	 * @param moduleMaster
	 * @throws Exception 
	 */
	public void createModule(Module moduleMaster) throws Exception {
		try {
			if (moduleMaster != null && moduleMaster.getModuleId() == 0) {
				log.debug("Going to add " + moduleMaster);
//				String sql = "INSERT INTO MODULE (module_name, description, env_id, create_user, create_date) "
				String sql = "INSERT INTO MODULE (module_name, description, create_user, create_date) "
						+ " VALUES (:module_name, :description, :create_user, :create_date);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("module_id", moduleMaster.getModuleId());
				parameters.put("module_name", moduleMaster.getModuleName());
				parameters.put("description", moduleMaster.getDescription());
//				parameters.put("env_id", moduleMaster.getEnvId());
				parameters.put("create_user", moduleMaster.getCreateUser());
				parameters.put("create_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(moduleMaster.getModuleName() + " not added");
			}
		} catch (Exception e) {
			log.error("Unable to create module for modulename: " + moduleMaster.getModuleName(), e);
			throw e;
		}
	}
	
	/**
	 * This method updates module into database
	 * @param moduleMaster
	 * @throws Exception 
	 */
	public void updateModule(Module moduleMaster) throws Exception {
		try {
			if (moduleMaster != null && moduleMaster.getModuleId() > 0) {
				log.debug("Going to update " + moduleMaster.getModuleName());
//				String sql = "update MODULE set description=:description, env_id=:env_id, "
				String sql = "update MODULE set description=:description, "
						+ "update_user=:update_user, update_date=:update_date where module_id=:module_id";
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("module_id", moduleMaster.getModuleId());
				parameters.put("module_name", moduleMaster.getModuleName());
//				parameters.put("env_id", moduleMaster.getEnvId());
				parameters.put("description", moduleMaster.getDescription());
				parameters.put("update_user", moduleMaster.getUpdateUser());
				parameters.put("update_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(moduleMaster.getModuleName() + " Module not updated");
			}
		} catch (Exception e) {
			log.error("Unable to update module for module_name: " + moduleMaster.getModuleName(), e);
			throw e;
		}
	}
	
	/**
	 * This method attaches module with environment into database
	 * @param moduleEnvMaster
	 * @throws Exception 
	 */
	public void attachModule(ModuleEnvironmentMaster moduleEnvMaster) throws Exception {
		try {
			if (moduleEnvMaster != null &&  moduleEnvMaster.getModuleId() > 0 && moduleEnvMaster.getEnvId() > 0) {
				String sql = "";
				if(moduleEnvMaster.isDeleteInd()) {
					log.debug("Going to delete module id: " + moduleEnvMaster.getModuleId() + " and environment ID: " + moduleEnvMaster.getEnvId() + " combination");
					sql = "Delete from MODULE_ENV where module_id = :module_id and env_id=:env_id ";
				} else {				
					log.debug("Going to attach module id: " + moduleEnvMaster.getModuleId() + " with environment ID: " + moduleEnvMaster.getEnvId());
					sql = "INSERT INTO MODULE_ENV (module_id, env_id, create_user, create_date) "
							+ " VALUES (:module_id, :env_id, :create_user, :create_date);";
				}
				

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("module_id", moduleEnvMaster.getModuleId());
				parameters.put("env_id", moduleEnvMaster.getEnvId());
				parameters.put("create_user", moduleEnvMaster.getCreateUser());
				parameters.put("create_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug("Attachment/Deletion of module id: " + moduleEnvMaster.getModuleId() + " with environment ID: " + moduleEnvMaster.getEnvId() + " is not done");
			}
		} catch (Exception e) {
			log.error("Unable to attach/delete module id: " + moduleEnvMaster.getModuleId() + " with environment ID: " + moduleEnvMaster.getEnvId(), e);
			throw e;
		}
	}
	
	/**
	 * This method retrieves module and environment combination from database
	 * @param moduleEnvMaster
	 * @throws Exception 
	 */
	public List<ModuleEnvironmentMaster> getModuleEnv(ModuleEnvironmentMaster moduleEnvMaster) throws Exception {
		List<ModuleEnvironmentMaster> moduleEnv = null;
		try {
			if (moduleEnvMaster != null) {
				String sql = "select me.module_env_id, me.module_id, me.env_id, m.module_name, em.env_name, "
						+ "me.create_user, me.create_date, me.update_user, me.update_date "
						+ " from MODULE_ENV me, MODULE m, ENVIRONMENT_MASTER em "
						+ "where me.module_id = m.module_id and me.env_id = em.env_id ";
				if(moduleEnvMaster.getModuleId() > 0 && moduleEnvMaster.getEnvId() > 0) {
					log.debug("Going to get module environments by module id: " + moduleEnvMaster.getModuleId() + " and env id: " + moduleEnvMaster.getEnvId());
					sql =  sql + " and me.module_id=:module_id and me.env_id=:env_id";
				}else if(moduleEnvMaster.getModuleId() > 0) {
					log.debug("Going to get module environments by module name: " + moduleEnvMaster.getModuleName());
					sql =  sql + " and me.module_id=:module_id";
				} else if (moduleEnvMaster.getEnvId() > 0) {
					log.debug("Going to get module environments by environment name: " + moduleEnvMaster.getEnvName());
					sql =  sql + " and me.env_id=:env_id";
				} 
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("module_id", moduleEnvMaster.getModuleId());
				parameters.put("env_id", moduleEnvMaster.getEnvId());

				moduleEnv = namedParameterJdbcTemplate.query(sql, parameters, new ModuleEnviromentMapper());
				
			} else {
				log.debug(" Invalid input. Details not retrieved ");
			}
		} catch (Exception e) {
			log.error("Unable to retrieve module data ", e);
			throw e;
		}
		return moduleEnv;
	}
	
	private static final class ModuleMapper implements RowMapper<Module> {

		public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
			Module moduleMaster = new Module();
			if(rs.getInt("module_id") > 0) moduleMaster.setModuleId(rs.getInt("module_id"));
			moduleMaster.setModuleName(rs.getString("module_name"));
			moduleMaster.setDescription(rs.getString("description"));
//			if(rs.getInt("env_id") > 0) moduleMaster.setEnvId(rs.getInt("env_id"));
			moduleMaster.setCreateUser(rs.getString("create_user"));
			moduleMaster.setUpdateUser(rs.getString("update_user"));
			moduleMaster.setCreateDate(rs.getTimestamp("create_date"));
			moduleMaster.setUpdateDate(rs.getTimestamp("update_date"));
			return moduleMaster;
		}
	}
	
	private static final class ModuleEnviromentMapper implements RowMapper<ModuleEnvironmentMaster> {

		public ModuleEnvironmentMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
			ModuleEnvironmentMaster moduleEnvMaster = new ModuleEnvironmentMaster();
			moduleEnvMaster.setModuleEnvId(rs.getInt("module_env_id"));
			moduleEnvMaster.setModuleId(rs.getInt("module_id"));
			moduleEnvMaster.setEnvId(rs.getInt("env_id"));
			moduleEnvMaster.setEnvName(rs.getString("env_name"));
			moduleEnvMaster.setModuleName(rs.getString("module_name"));
			moduleEnvMaster.setCreateUser(rs.getString("create_user"));
			moduleEnvMaster.setUpdateUser(rs.getString("update_user"));
			moduleEnvMaster.setCreateDate(rs.getTimestamp("create_date"));
			moduleEnvMaster.setUpdateDate(rs.getTimestamp("update_date"));
			return moduleEnvMaster;
		}
	}

}
