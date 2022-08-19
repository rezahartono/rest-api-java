package com.restapi.vo;

public class SettingConstant {
  
	public static String METADATA = "meta";
	public static String DATA = "data";
	public static String PAGINATION = "pagination";
	public static Integer ROWS_PER_PAGE = 10;
	
	public static class ResponseMetadata{
		public static String PATH = "path";
		public static String STATUS_CODE = "status_code";
		public static String STATUS = "status";
		public static String MESSAGES = "messages";
	}
	
	public static class ResponsePagination{
		public static String TOTAL_PAGES = "total_pages";
		public static String CURRENT_PAGE = "current_page";
		public static String TOTAL_ELEMENTS = "total_elements";
		public static String CURRENT_ELEMENT = "current_element";
	}
	
	public static class JwtConstant{
		public static String TOKEN_TYPE = "Bearer";
		public static String AUTHORIZATION = "Authorization";
		public static String SECRET_CODE = "SALAMANAT123!";
		public static String AUTHOR = "Admin45";
	}
}
