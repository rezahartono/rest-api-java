package com.restapi.controller;

import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class RestController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hallo() {
		return "Haloooo";
	}
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Map<String, String> obj) {
		return AuthenticationController.getInstance().login(obj);
	}
	
	@Path("logout")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@HeaderParam("Authorization") String authorization) {
		return AuthenticationController.getInstance().logout(authorization);
	}
	
	@Path("get-books")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListBooks(@HeaderParam("Authorization") String authorization) {
		return BookController.getInstance().getListBooks(authorization);
	}
}
