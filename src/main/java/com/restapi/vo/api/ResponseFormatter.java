package com.restapi.vo.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.restapi.vo.SettingConstant;

public class ResponseFormatter {

  ResponseData DATA = new ResponseData();
  Map<String, Object> RESPONSE_DATA = new HashMap<String, Object>();
  
  public Map<String, Object> OK(Object responseData, String path, String messages, int totalPages, int currentPage, int totalElements, int currentElement){
	  RESPONSE_DATA.clear();
	  
	  RESPONSE_DATA.put(SettingConstant.METADATA, DATA.METADATA(path, Response.Status.OK.getStatusCode(), Response.Status.OK.toString(), messages));
	  RESPONSE_DATA.put(SettingConstant.DATA, responseData);
	  RESPONSE_DATA.put(SettingConstant.PAGINATION, DATA.PAGINATION(totalPages, currentPage, totalElements, currentElement));
	  
	  return RESPONSE_DATA;
  }
  
  public Map<String, Object> UNAUTHORIZED(String path, String messages, int totalPages, int currentPage, int totalElements, int currentElement){
	  RESPONSE_DATA.clear();
	  
	  RESPONSE_DATA.put(SettingConstant.METADATA, DATA.METADATA(path, Response.Status.UNAUTHORIZED.getStatusCode(), Response.Status.UNAUTHORIZED.toString(), messages));
//	  RESPONSE_DATA.put(SettingConstant.DATA, responseData);
	  RESPONSE_DATA.put(SettingConstant.PAGINATION, DATA.PAGINATION(totalPages, currentPage, totalElements, currentElement));
	  
	  return RESPONSE_DATA;
  }
}
