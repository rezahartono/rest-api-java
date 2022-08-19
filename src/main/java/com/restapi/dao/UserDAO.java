package com.restapi.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.restapi.common.CommonGeneric;
import com.restapi.vo.UserVO;

public class UserDAO extends CommonGeneric {

	private static UserDAO _UserDAO;

	public static UserDAO getInstance() {
		_UserDAO = new UserDAO();

		return _UserDAO;
	}

	private Statement stmt = dbConnection();

	public List<UserVO> loginUser(Map<?, ?> map) {
		List<UserVO> users = new ArrayList<UserVO>();

		String query = "SELECT * FROM users WHERE username = '" + map.get("username") + "' AND password = '"
				+ map.get("password") + "'";

		System.out.println("query ===> " + query);

		try {
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				UserVO user = new UserVO();

				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setUsername(res.getString("username"));
				user.setPassword(res.getString("password"));

				System.out.println(user);

				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return users;
	}

	public String generateSession(int userId) {
		String sessionId = null;

		String newSession = UUID.randomUUID().toString();

		String query = "UPDATE `users` SET `session_id` = '" + newSession + "' WHERE `id` = '" + userId + "'";

		System.out.println(query);

		try {
			int res = stmt.executeUpdate(query);

			System.out.println(res);

			if (res >= 1) {
				sessionId = newSession;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return sessionId;
	}

	public boolean deleteSession(String userId) {
		boolean result = false;

		String query = "UPDATE `users` SET `session_id` = '' WHERE `id` = '" + userId + "'";

		System.out.println(query);

		try {
			int res = stmt.executeUpdate(query);

			System.out.println(res);

			if (res >= 1) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String getSession(String userId) {
		String result = null;

		String query = "SELECT `session_id` FROM users WHERE `id` = '" + userId +"'";

		System.out.println(query);

		try {
			ResultSet res = stmt.executeQuery(query);

			System.out.println(res);

			if(res.next()) {
				result = res.getString("session_id");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}
}
