package com.webservices.marshmello;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONObject;

@Path("/UserService")
public class Person {

	//Get all users
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/users")
	 public String getUsers() {
	   return "{\"name\":\"gerben\", \"name\":\"marcel\"} ";
	 }
	 
	 //Get user by ID
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/users/{userid}")
	 public String getpersonbyid(@PathParam("userid") int userid) {
		 JSONArray array = new JSONArray();
		 JSONObject obj = new JSONObject();
		 JSONObject obj1 = new JSONObject();
		 
		 obj1.put("test", "tests");
		 obj1.put("test1", "tests");
		 obj1.put("test2", "tests");
		 
		 array.add(obj1);
		 obj.put("name", "foo");
	      obj.put("num", new Integer(100));
	      obj.put("balance", new Double(1000.21));
	      obj.put("is_vip", new Boolean(true));
	      obj.put("def", array);
	   return obj.toString();
	 }
	 
	//Insert user
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("/insert")
	 public String insertUser(String tests) {
	   return tests;
	 }
	 
	 
	//Delete user
	 @DELETE
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("/delete")
	 public String deleteUser(String tests) {
	   return tests+"asds";
	 }
}
