package com.ashish.poc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.poc.model.EnvironmentMaster;
import com.ashish.poc.model.Module;
import com.ashish.poc.model.Role;
import com.ashish.poc.model.Url;
import com.ashish.poc.model.UserDataModel;

@Component
public class ExcelUploadServices {
private static final Logger log = Logger.getLogger(ExcelUploadServices.class);
	
	@Autowired
	private EnvironmentServices environmentServices;
	
	@Autowired
	private ModuleServices moduleServices;
	
	@Autowired
	private RoleServices roleServices;
	
	@Autowired
	private UrlServices urlServices;
	
	@Transactional
	public void uploadExcel(UserDataModel udm) throws Exception {
		String FILE_NAME = udm.getFileName();// "C:\\Users\\U49676\\workspace_luna\\userguide\\DataUploadTemplate.xlsx";
		FileInputStream excelFile = null;
		Workbook workbook = null;
		try {

	            excelFile = new FileInputStream(new File(FILE_NAME));
	            workbook = new XSSFWorkbook(excelFile);
	            Sheet datatypeSheet = workbook.getSheetAt(0);
	            Iterator<Row> iterator = datatypeSheet.iterator();
	            Map<String, Integer> projectMap = new HashMap<String, Integer>();
	            Map<String, Integer> environmentMap = new HashMap<String, Integer>();
	            Map<String, Integer> roleMap = new HashMap<String, Integer>();
	            
	            boolean isFirstRow = true;
	            int colCount = 0;
	            int projectId = 0;
	            int envId = 0;
	            int roleId = 0;
	            
	            while (iterator.hasNext()) {
	                Row currentRow = iterator.next();
	                if(isFirstRow) { // Skip the first row
	                	isFirstRow = false;
	                	continue;
	                }
	                if(currentRow.getRowNum() > 100) {
	                	log.info("Do not upload more than 100 data");
	                	udm.setErrorMsg("Total 100 rows have been uploaded");
	                	break;
	                }
	                colCount = 0;
//	                Iterator<Cell> cellIterator = currentRow.iterator();
	                
	                Url url = new Url();
	                while (colCount < 9) {
	                	Cell cell = currentRow.getCell(colCount);
	                	
	                	if(colCount == 0) { // First cell must have project name
	                		if(cell == null) {
//		                		System.out.println("Record in row number # " + (currentRow.getRowNum() + 1) + " is invalid. Project name is missing");
		                		log.info("Record in row number # " + (currentRow.getRowNum() + 1) + " is invalid. Project name is missing: ");
		                		break;
	                		} else {
	                			// Find the project id (module id)
	                			if(projectMap != null && projectMap.get(cell.getStringCellValue()) == null) {
		                			List<Module> moduleList = new ArrayList<Module>();
		                			Module m = new Module();
		                			m.setModuleName(cell.getStringCellValue());
		                			moduleList.add(m);
		                			udm.setModules(moduleList);
		                			udm.setErrorMsg(null); // Setting error message before service call to null
		                			moduleServices.getModules(udm);
		                			if(udm.getErrorMsg() == null) {
		                				projectMap.put(cell.getStringCellValue(), udm.getModules().get(0).getModuleId());
		                				//System.out.println("Module id : " + udm.getModules().get(0).getModuleId());
		                			}
	                			}
	                			
	                			if(projectMap.get(cell.getStringCellValue()) != null) {
		                			projectId = projectMap.get(cell.getStringCellValue());
		                			url.setModuleId(projectId);
		                		}
	                		}
	                	} else if (colCount == 1) {
	                		// Find the env id (if any)
	                		if(environmentMap != null && cell != null && environmentMap.get(cell.getStringCellValue()) == null) {
	                			List<EnvironmentMaster> envList = new ArrayList<EnvironmentMaster>();
	                			EnvironmentMaster e = new EnvironmentMaster();
	                			e.setEnvName(cell.getStringCellValue());
	                			envList.add(e);
	                			udm.setEnvironments(envList);
	                			udm.setErrorMsg(null); // Setting error message before service call to null
	                			environmentServices.getEnvironments(udm);
	                			if(udm.getErrorMsg() == null) {
	                				environmentMap.put(cell.getStringCellValue(), udm.getEnvironments().get(0).getEnvId());
	                				//System.out.println("Environment id : " + udm.getEnvironments().get(0).getEnvId());
	                			}
                			}
	                		
	                		if(environmentMap.get(cell.getStringCellValue()) != null) {
	                			envId = environmentMap.get(cell.getStringCellValue());
	                			url.setEnvId(envId);
	                		}
	                	} else if (colCount == 2) {
	                		if(cell != null) url.setName(cell.getStringCellValue());
	                	} else if (colCount == 3) {
	                		if(cell != null) url.setDescription(cell.getStringCellValue());
	                	} else if (colCount == 4) { // Check if URL already exists
	                		String urlStr = cell.getStringCellValue();
	                		List<Url> urlList = new ArrayList<Url>();
                			Url u = new Url();
                			u.setUrl(urlStr);
                			urlList.add(u);
	                		udm.setUrls(urlList);
	                		udm.setErrorMsg(null); // Setting error message before service call to null
	                		urlServices.getUrl(udm);
	                		
	                		if(udm.getErrorMsg() == null) { // URL already exists into the database. Skip the row
	                			log.info("URL: " + urlStr + " already exists into database. Skipping the record");
//	                			System.out.println("URL: " + urlStr + " already exists into database. Skipping the record");
	                			break;
	                		}
	                		url.setUrl(urlStr);
	                	} else if (colCount == 5) {
	                		if(cell != null) url.setUsername(cell.getStringCellValue());
	                	} else if (colCount == 6) {
	                		if(cell != null) url.setPassword(cell.getStringCellValue());
	                	} else if (colCount == 7) {
	                		boolean isVisible = true;
	                		if(cell != null && cell.getStringCellValue() != null && cell.getStringCellValue().trim().equalsIgnoreCase("false")) {
	                			isVisible = false;
	                		}
	                		url.setVisible(isVisible);
	                	} else if (colCount == 8) {
	                		String visibleToRole = "ALL";
	                		if(cell != null && cell.getStringCellValue() != null && !cell.getStringCellValue().trim().equalsIgnoreCase("")) {
	                			visibleToRole = cell.getStringCellValue();
	                		}
	                		
	                		
	                		
	                		if(roleMap != null && cell != null && roleMap.get(cell.getStringCellValue()) == null) {
	                			List<Role> roleList = new ArrayList<Role>();
	                			Role u = new Role();
	                			u.setRoleName(visibleToRole);
	                			roleList.add(u);
		                		udm.setRoles(roleList);
		                		udm.setErrorMsg(null); // Setting error message before service call to null
		                		roleServices.getRoles(udm);
	                			if(udm.getErrorMsg() == null) {
	                				roleMap.put(visibleToRole, udm.getRoles().get(0).getRoleId());
	                			}
                			}
	                		
	                		if(roleMap.get(visibleToRole) != null) {
	                			roleId = roleMap.get(visibleToRole);
	                			url.setRoleId(roleId);
	                		}
	                	}
	                	
	                	if (colCount == 8) { // Required data is prepared at this point
	                		if(projectId > 0 && envId > 0) {
	                			List<Url> urlList = new ArrayList<Url>();
	                			urlList.add(url);
		                		udm.setUrls(urlList);
		                		
		                		urlServices.createOrUpdateUrl(udm);
//		                		System.out.println("Inserting URL: " + url);
		                		log.info("Inserting URL: " + url);
	                		} else {
//	                			System.out.println("Project or Environment can not be blank");
	                			log.info("Project or Environment can not be blank");
	                		}
	                	}
	                	colCount++;
	                	
	                	 
	                }
	            }
	        } catch (FileNotFoundException e) {
	            udm.setErrorMsg("Unable to upload the excel into database");
	            log.error("Unable to upload the excel into database", e);
	        } catch (IOException e) {
	        	udm.setErrorMsg("Unable to upload the excel into database");
	            log.error("Unable to upload the excel into database", e);
	        } finally {
	        	if(workbook != null) {
	        		workbook.close();
	        	}
	        	if(excelFile != null) {
	        		excelFile.close();
	        	}
	        }
	}
	
	
	public static void main(String args[]) {
		ExcelUploadServices a = new ExcelUploadServices();
		try {
			//a.uploadExcel(null);
			String fileName = "abc.txt";
            
            String file1StName = fileName.substring(0, fileName.lastIndexOf("."));
            String extn = fileName.substring(fileName.lastIndexOf("."));
            fileName = file1StName + "_" + new Date().getTime() + extn;
            System.out.println(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
