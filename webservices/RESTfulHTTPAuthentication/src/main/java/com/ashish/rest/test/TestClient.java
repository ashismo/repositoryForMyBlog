package com.ashish.rest.test;

import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestClient {

  public static void main(String[] args) {

	  testPostWithoutBasicAuth();
	  testGETWithBasicAuth();
	  testPOSTWithBasicAuth();
 }

  /**
   * Below method is used to test GET request with HTTP Basic authentication in the header of the request
   */
	private static void testGETWithBasicAuth() {
		try {
	
	        Client client = Client.create();
	
	        String name = "admin";
	        String password = "admin";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authStringEnc);
	        WebResource webResource = client.resource("http://localhost:8080/RESTfulAuth/rest/hello/getEmployee/123");
	        
	        ClientResponse resp = webResource.accept("application/json")
	                                         .header("Authorization", "Basic " + authStringEnc)
	                                         .get(ClientResponse.class);
	        if(resp.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = resp.getEntity(String.class);
	        System.out.println("Response for the GET with HTTP Basic authentication request: "+output);
	        System.out.println("=========================================================================");
	
	      } catch (Exception e) {
	
	        e.printStackTrace();
	
	      }
	}
	
	/**
	 * Below method is used to test POST request with HTTP Basic authentication in the header of the request
	 */
	private static void testPOSTWithBasicAuth() {
		try {
	
	        Client client = Client.create();
	
	        String name = "admin";
	        String password = "admin";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authStringEnc);
	        WebResource webResource = client.resource("http://localhost:8080/RESTfulAuth/rest/hello/getSalary");
	        
	        ClientResponse resp = webResource.accept("application/json")
	                                         .header("Authorization", "Basic " + authStringEnc)
	                                         .post(ClientResponse.class);
	        if(resp.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = resp.getEntity(String.class);
	        System.out.println("Response for the POST with HTTP Basic authentication request: "+output);
	        System.out.println("=========================================================================");
	
	      } catch (Exception e) {
	
	        e.printStackTrace();
	
	      }
	}
	
	/**
	 * Below method is used to test POST request without HTTP Basic authentication in the header of the request
	 */
	private static void testPostWithoutBasicAuth() {
		try {
	
	        Client client = Client.create();
	
	        WebResource webResource = client.resource("http://localhost:8080/RESTfulAuth/rest/hello/getSalary");
	
	        String input = "{\"empId\":\"123\"}";
	
	        ClientResponse response = webResource.type("application/json")
	           .post(ClientResponse.class, input);
	
	        if (response.getStatus() != 201) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                 + response.getStatus());
	        }
	
	        System.out.println("HTTP Basic authentication error .... \n");
	        String output = response.getEntity(String.class);
	        System.out.println(output);
	        System.out.println("=========================================================================");
	      } catch (Exception e) {
	
	        e.printStackTrace();
	
	      }
	}
}