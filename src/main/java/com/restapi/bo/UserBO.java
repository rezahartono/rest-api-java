package com.restapi.bo;

import java.util.*;

import com.restapi.dao.UserDAO;
import com.restapi.vo.UserVO;

public class UserBO extends UserDAO {
	private static UserBO _UserBO;

	public static UserBO getInstance() {
		_UserBO = new UserBO();

		return _UserBO;
	}

	public UserVO loginUser(String username, String password) {
		UserVO user = new UserVO();
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);

		try {
			List<UserVO> users = loginUser(map);

			if (users != null) {
				user = users.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return user;
	}

	public String generateSession(int userId) {
		String sessionId = null;

		try {
			sessionId = UserDAO.getInstance().generateSession(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sessionId;
	}

	public boolean deleteSession(String userId) {
		boolean result = false;

		try {
			result = UserDAO.getInstance().deleteSession(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String sessionCheck(String userId, String sessionId) {
		String result = null;

		try {
			result = UserDAO.getInstance().getSession(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}
}
