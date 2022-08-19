package com.restapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.restapi.bo.BookBO;
import com.restapi.common.ServiceCommon;
import com.restapi.vo.BookVO;
import com.restapi.vo.api.ResponseFormatter;

public class BookController extends ServiceCommon {
	private static BookController _BookController;

	public static BookController getInstance() {
		_BookController = new BookController();

		return _BookController;
	}

	ResponseFormatter rf = new ResponseFormatter();

	public Response getListBooks(String authToken) {
		Response res = null;
		Map<String, String> authenticationData = new HashMap<String, String>();
		List<BookVO> books = new ArrayList<BookVO>();

		if (authToken != null) {
			authenticationData = decodeToken(authToken);
			
			if(isActiveSession(authenticationData.get("user_id"), authenticationData.get("session_id"))) {
				books = BookBO.getInstance().getListBooks();
				
				if(books.size() >= 1) {
					res = Response.ok().entity(rf.OK(books, "get-books", "get data success", 0, 0, 0, 0)).build();
				}
			}else {
				res = Response.status(408).entity(rf.UNAUTHORIZED("get-books", "Your access has expired", 0, 0, 0, 0)).build();
			}
		} else {
			res = Response.status(401).entity(rf.UNAUTHORIZED("get-books", "You can't be access this api", 0, 0, 0, 0)).build();
		}

		return res;
	}
}
