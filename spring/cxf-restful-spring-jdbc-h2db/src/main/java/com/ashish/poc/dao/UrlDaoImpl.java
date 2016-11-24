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

import com.ashish.poc.model.Url;
import com.ashish.poc.util.CommonUtil;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Repository
public class UrlDaoImpl {

	private static final Logger log = Logger.getLogger(UrlDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private PasswordEncodeDecodeUtil passwordEncodeDecode;
	
	public Url findByUrl(String urlName) throws Exception {
		Url url = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("url", urlName.toLowerCase());

			String sql = "SELECT  url.url_id, url.role_id, r.rolename, url.module_env_id, m.module_name, "
					+ " em.env_name, url.name,url.description,url.url,url.username,url.password, url.email,url.visible, "
					+ "url.create_user, url.update_user, url.create_date, url.update_date "
					+ " FROM URL url "
					+ "left outer join MODULE_ENV me on  url.module_env_id = me.module_env_id "
					+ "left outer join MODULE m on me.module_id = m.module_id "
					+ "left outer join ENVIRONMENT_MASTER em on me.env_id = em.env_id "
					+ "left outer join ROLE r on   url.role_id = r.role_id "
					+ "where lower(url.url)=:url";

			url = namedParameterJdbcTemplate.queryForObject(sql, params,new UrlMapper());

		} catch (Exception e) {
			log.error("No records found for url name: " + urlName, e);
		}
		return url;

	}
	
	public Url findByUrlId(int urlId) throws Exception {
		Url result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("url_id", urlId);

			String sql = "SELECT  url.url_id, url.role_id, r.rolename, url.module_env_id, m.module_name, "
					+ " em.env_name, url.name,url.description,url.url,url.username,url.password, url.email,url.visible, "
					+ "url.create_user, url.update_user, url.create_date, url.update_date "
					+ " FROM URL url "
					+ "left outer join MODULE_ENV me on  url.module_env_id = me.module_env_id "
					+ "left outer join MODULE m on me.module_id = m.module_id "
					+ "left outer join ENVIRONMENT_MASTER em on me.env_id = em.env_id "
					+ "left outer join ROLE r on   url.role_id = r.role_id "
					+ "where lower(url.url_id)=:url_id";

			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UrlMapper());

		} catch (Exception e) {
			log.error("No records found for url id: " + urlId, e);
		}
		return result;

	}

	public List<Url> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT  url.url_id, url.role_id, r.rolename, url.module_env_id, m.module_name, "
				+ " em.env_name, url.name,url.description,url.url,url.username,url.password, url.email,url.visible, "
				+ "url.create_user, url.update_user, url.create_date, url.update_date "
				+ " FROM URL url "
				+ "left outer join MODULE_ENV me on  url.module_env_id = me.module_env_id "
				+ "left outer join MODULE m on me.module_id = m.module_id "
				+ "left outer join ENVIRONMENT_MASTER em on me.env_id = em.env_id "
				+ "left outer join ROLE r on   url.role_id = r.role_id ";
				

		List<Url> result = namedParameterJdbcTemplate.query(sql, params, new UrlMapper());

		return result;

	}

	/**
	 * This method creates URL into database
	 * @param url
	 * @throws Exception 
	 */
	public void createUrl(Url url) throws Exception {
		try {
			if (url != null && url.getUrlId() == 0) {
				log.debug("Going to add " + url.getUrl());
				String sql = "INSERT INTO URL (name, description,url,username,password,module_env_id,role_id,visible,email, create_user, create_date) "
						+ " VALUES (:name, :description, :url, :username, :password, :module_env_id, :role_id, :visible, :email, :create_user, :create_date);";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("name", url.getName());
				parameters.put("description", url.getDescription());
				parameters.put("url", url.getUrl());
				parameters.put("username", url.getUsername());
				parameters.put("password", passwordEncodeDecode.base64encode(url.getPassword()));
				parameters.put("module_env_id", url.getModuleEnvId());
				parameters.put("role_id", url.getRoleId());
				parameters.put("visible", url.isVisible());
				parameters.put("email", url.getEmail());
				parameters.put("create_user", url.getCreateUser());
				parameters.put("create_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(url.getRoleName() + " not added");
			}
		} catch (Exception e) {
			log.error("Unable to create url for url name: " + url.getName(), e);
			throw e;
		}
	}
	
	/**
	 * This method updates URL into database
	 * @param url
	 * @throws Exception 
	 */
	public void updateUrl(Url url) throws Exception {
		try {
			if (url != null && url.getUrlId() > 0) {
				log.debug("Going to update " + url.getName());
				String sql = "update URL set name=:name, description=:description, url=:url,"
						+ "username=:username,password=:password,module_env_id=:module_env_id,role_id=:role_id,visible=:visible,email=:email,"
						+ "update_user=:update_user, update_date=:update_date where url_id=:url_id";
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("url_id", url.getUrlId());
				parameters.put("name", url.getName());
				parameters.put("description", url.getDescription());
				parameters.put("url", url.getUrl());
				parameters.put("username", url.getUsername());
				parameters.put("password", passwordEncodeDecode.base64encode(url.getPassword()));
				parameters.put("module_env_id", url.getModuleEnvId());
				parameters.put("role_id", url.getRoleId());
				parameters.put("visible", url.isVisible());
				parameters.put("email", url.getEmail());
				parameters.put("update_user", url.getUpdateUser());
				parameters.put("update_date", commonUtil.getCurrentTimeStamp());

				namedParameterJdbcTemplate.update(sql, parameters);
			} else {
				log.debug(url.getRoleName() + " URL not updated");
			}
		} catch (Exception e) {
			log.error("Unable to update URL for url_name: " + url.getName(), e);
			throw e;
		}
	}
	
	private static final class UrlMapper implements RowMapper<Url> {
		private PasswordEncodeDecodeUtil passwordEncodeDecodeUtil = new PasswordEncodeDecodeUtil();
		public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
			Url url = new Url();
			url.setUrlId(rs.getInt("url_id"));
			if(rs.getInt("role_id") > 0) url.setRoleId(rs.getInt("role_id"));
			if(rs.getInt("module_env_id") > 0) url.setModuleEnvId(rs.getInt("module_env_id"));
			url.setRoleName(rs.getString("rolename"));
			url.setModuleName(rs.getString("module_name"));
			url.setEnvName(rs.getString("env_name"));
			url.setName(rs.getString("name"));
			url.setUrl(rs.getString("url"));
			url.setDescription(rs.getString("description"));
			url.setUsername(rs.getString("username"));
			if(rs.getString("password") != null) {
				url.setPassword(passwordEncodeDecodeUtil.base64decode(rs.getString("password")));
			}
			url.setEmail(rs.getString("email"));
			url.setVisible(rs.getBoolean("visible"));
			url.setCreateUser(rs.getString("create_user"));
			url.setUpdateUser(rs.getString("update_user"));
			url.setCreateDate(rs.getTimestamp("create_date"));
			url.setUpdateDate(rs.getTimestamp("update_date"));
			return url;
		}
	}

}
