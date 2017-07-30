package com.ashish.poc.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.impl.HttpHeadersImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.services.EnvironmentServices;
import com.ashish.poc.services.ExcelUploadServices;
import com.ashish.poc.services.ModuleServices;
import com.ashish.poc.services.RoleServices;
import com.ashish.poc.util.CommonUtil;

@Path("/master")
@Controller
public class MasterDataWSImpl {
	
	private static final Logger log = Logger.getLogger(MasterDataWSImpl.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private RoleServices roleServices;
	
	@Autowired
	private EnvironmentServices envServices;
	
	@Autowired
	private ModuleServices moduleServices;

	@Autowired
	private ExcelUploadServices excelUploadServices;

	@POST
	@Path("/createRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createRole(UserDataModel userDataModel)
			throws Exception {
		try {
			roleServices.createOrUpdateRole(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update role", e);
			userDataModel.setErrorMsg("Unable to create/update role");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getRoles(UserDataModel userDataModel)
			throws Exception {
		try {
			roleServices.getRoles(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve role data", e);
			userDataModel.setErrorMsg("Unable to retrieve role data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/createEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@WebMethod
	public UserDataModel createEnv(@Context HttpHeadersImpl headers, UserDataModel userDataModel)
			throws Exception {
		
		try {
			envServices.createOrUpdateEnvironments(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update environment", e);
			userDataModel.setErrorMsg("Unable to create/update environment");
		}
		return userDataModel;
	}
	
	
	@POST
	@Path("/getEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getEnvironments(UserDataModel userDataModel)
			throws Exception {
		try {
			envServices.getEnvironments(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve environment data", e);
			userDataModel.setErrorMsg("Unable to retrieve environment data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/createModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createModule(UserDataModel userDataModel)
			throws Exception {
		
		try {
			moduleServices.createOrUpdateModules(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update module", e);
			userDataModel.setErrorMsg("Unable to create/update module");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getModule(UserDataModel userDataModel)
			throws Exception {
		try {
			moduleServices.getModules(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve module data", e);
			userDataModel.setErrorMsg("Unable to retrieve module data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/attachModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel attachModule(UserDataModel userDataModel)
			throws Exception {
		
		try {
			moduleServices.attachOrDetachModuleAndEnv(userDataModel);
		} catch (Exception e) {
			log.error("Unable to attache/detach module with environment", e);
			userDataModel.setErrorMsg("Unable to attache/detach module with environment");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getModuleEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getModuleEnv(UserDataModel userDataModel)
			throws Exception {
		try {
			moduleServices.getModuleEnvironment(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve module env data", e);
			userDataModel.setErrorMsg("Unable to retrieve module env data");
		}
		return userDataModel;
	}
	
	@GET
	@Path("/uploadExcel")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDataModel uploadExcel()
			throws Exception {
		UserDataModel userDataModel = new UserDataModel();
		try {
			excelUploadServices.uploadExcel(userDataModel);
		} catch (Exception e) {
			log.error("Unable to upload data from excel", e);
			userDataModel.setErrorMsg("Unable to upload data from excel");
		}
		return userDataModel;
	}
	
	@Path("/uploadExcelData")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(List<Attachment> attachments,@Context HttpServletRequest request) throws URISyntaxException {
		final String PATH = env.getProperty("url_upload_path");
		for(Attachment attr : attachments) {
			DataHandler handler = attr.getDataHandler();
			InputStream stream = null;
			OutputStream out = null;
			try {
                stream = handler.getInputStream();
                MultivaluedMap map = attr.getHeaders();
                String fileName = getFileName(map);
                if(fileName.equals("unknown")) {
                	continue;
                }
                String file1StName = fileName.substring(0, fileName.lastIndexOf("."));
                String extn = fileName.substring(fileName.lastIndexOf("."));
                fileName = file1StName + "_" + new Date().getTime() + extn;
                File f = new File(PATH + fileName);
                out = new FileOutputStream(f);
 
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                stream.close();
                out.flush();
                out.close();
                
                UserDataModel udm = new UserDataModel();
                udm.setFileName(f.getAbsolutePath());
                excelUploadServices.uploadExcel(udm);
            } catch(Exception e) {
              log.error("File upload failed", e);
            } finally {
            	try {
	            	if(stream != null) stream.close();
	                if(out != null) out.flush();
	                if(out != null) out.close();
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
		}
		
	    java.net.URI location = new java.net.URI("../../../dashboard.jsp");

		return Response.temporaryRedirect(location).build();
		//return Response.ok("file uploaded").build();
    }
	
	
	private String getFileName(MultivaluedMap<String, String> header) {
		 
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
 
                String[] name = filename.split("=");
 
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

	
	   @GET
	   @Path("/downloadFile")
	   @Produces({"application/vnd.ms-excel", "application/json"})
	   public Response downloadFile(@QueryParam("isFailed") boolean isFailed) {
	      File file = new File("C:\\Ashish\\logs\\SomeFile.xlsx");
	      ResponseBuilder response = null;
	      try {
		      response = Response.ok((Object) file);
		      if(isFailed) {
		    	  response = null;
		      }
		      response.header("Content-Disposition", "attachment; filename=abcd.xlsx");
		      response.header("Content-Type", "application/vnd.ms-excel");
	      } catch(Exception e) {
	    	  response = Response.ok("Error Occured");
	    	  response.header("Content-Type", "application/json");
	      }
	      return response.build();
	   }
	   
	   
}
