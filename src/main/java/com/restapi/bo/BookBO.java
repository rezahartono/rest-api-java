package com.restapi.bo;

import java.util.ArrayList;
import java.util.List;

import com.restapi.dao.BookDAO;
import com.restapi.vo.BookVO;

public class BookBO extends BookDAO {
	private static BookBO _BookBO;

	public static BookBO getInstance() {
		_BookBO = new BookBO();

		return _BookBO;
	}
	
	public List<BookVO> getListBooks(){
		List<BookVO> books = new ArrayList<BookVO>();
		
		try {
			books = BookDAO.getInstance().getListBooks();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return books;
	}
}
