package com.restapi.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.restapi.vo.BookVO;
import com.restapi.vo.SettingConstant;
import com.restapi.common.CommonGeneric;

public class BookDAO extends CommonGeneric {
	private static BookDAO _BooksDAO;

	public static BookDAO getInstance() {
		_BooksDAO = new BookDAO();

		return _BooksDAO;
	}
	
	private Statement stmt = dbConnection();
	
	public List<BookVO> getListBooks() {
		List<BookVO> result = new ArrayList<BookVO>();
		
		String query = "SELECT * FROM `books` LIMIT "+SettingConstant.ROWS_PER_PAGE+"";
		
		System.out.println("query ==> "+query);
		
		try {
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				BookVO book = new BookVO();
				
				book.setId(res.getInt("id"));
				book.setName(res.getString("name"));
				book.setDescription(res.getString("description"));
				book.setTotal_pages(res.getInt("total_pages"));
				book.setVendor(res.getString("vendor"));
				book.setCreated_date(res.getString("release_date"));
				book.setWeight(res.getDouble("weight"));
				book.setWide(res.getDouble("wide"));
				book.setNumber(res.getString("number"));
				book.setRating(res.getDouble("rating"));
				book.setPrice(res.getDouble("price"));
				book.setPublisher(res.getString("publisher"));
				
				result.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("list ===> "+result);
		
		return result;
	}
}
