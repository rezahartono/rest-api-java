package com.restapi;

import java.util.*;

import javax.ws.rs.core.Application;

import com.restapi.controller.RestController;

public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(RestController.class);
		return classes;
	}
}