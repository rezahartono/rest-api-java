package com.restapi.vo.api;

import java.util.HashMap;
import java.util.Map;

import com.restapi.vo.SettingConstant;

public class ResponseData {

	public Map<String, Object> METADATA(String path, int statusCode, String status, String messages){
		Map<String, Object> meta = new HashMap<String, Object>();
		
		meta.clear();
		meta.put(SettingConstant.ResponseMetadata.PATH, path);
		meta.put(SettingConstant.ResponseMetadata.STATUS_CODE, statusCode);
		meta.put(SettingConstant.ResponseMetadata.STATUS, status);
		meta.put(SettingConstant.ResponseMetadata.MESSAGES, messages);
		
		return meta;
	}
	
	public Map<String, Object> PAGINATION(int totalPages, int currentPage, int totalElements, int currentElement){
		Map<String, Object> pagination = new HashMap<String, Object>();
		
		pagination.clear();
		pagination.put(SettingConstant.ResponsePagination.TOTAL_PAGES, totalPages);
		pagination.put(SettingConstant.ResponsePagination.CURRENT_PAGE, currentPage);
		pagination.put(SettingConstant.ResponsePagination.TOTAL_ELEMENTS, totalElements);
		pagination.put(SettingConstant.ResponsePagination.CURRENT_ELEMENT, currentElement);

		return pagination;
	}
}
