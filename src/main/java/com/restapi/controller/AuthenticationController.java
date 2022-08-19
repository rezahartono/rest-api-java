package com.restapi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.restapi.bo.UserBO;
import com.restapi.common.ServiceCommon;
import com.restapi.vo.SettingConstant;
import com.restapi.vo.UserVO;
import com.restapi.vo.api.ResponseFormatter;
import com.restapi.vo.api.ResponseJwt;

public class AuthenticationController extends ServiceCommon {

	private static AuthenticationController _AuthenticationController;

	public static AuthenticationController getInstance() {
		_AuthenticationController = new AuthenticationController();

		return _AuthenticationController;
	}

	public Response login(Map<?, ?> obj) {
		String username = null, password = null, token = null, sessionId = null;
		UserVO user = new UserVO();
		Map<String, Object> result = new HashMap<String, Object>();
		ResponseFormatter rf = new ResponseFormatter();

		if (!obj.isEmpty()) {
			username = obj.get("username").toString();
			password = obj.get("password").toString();
			ResponseJwt tokenData = new ResponseJwt();

			try {
				user = UserBO.getInstance().loginUser(username, password);

				if (user != null) {
					sessionId = UserBO.getInstance().generateSession(user.getId());

					System.out.println(sessionId);

					token = generateToken(user.getId(), sessionId);
					tokenData.setToken(token);
					tokenData.setToken_type(SettingConstant.JwtConstant.TOKEN_TYPE);

					result.putAll(rf.OK(tokenData, "/login", "Authentication Success", 0, 0, 0, 0));
					;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (user != null) {
				return Response.ok().entity(result).build();
			} else {
				return Response.status(404).entity("user not available").build();
			}
		} else {
			return Response.status(404).entity("User Not Available").build();
		}

	}

	public Response logout(String authorization) {
		Map<String, String> data = new HashMap<String, String>();
		Map<String, Object> resultData = new HashMap<String, Object>();
		ResponseFormatter rf = new ResponseFormatter();
		
		Response response = null;
		
		try {
			data = decodeToken(authorization);
			
			if(data != null) {
				if(UserBO.getInstance().deleteSession(data.get("user_id"))) {
					resultData.putAll(rf.OK(null, "/logout", "Logout Success", 0, 0, 0, 0));
					response = Response.ok().entity(resultData).build();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			resultData.putAll(rf.OK(null, "/logout","You are already logout", 0, 0, 0, 0));
			response = Response.ok().entity(resultData).build();
		}
		
		return response;
	}

}
