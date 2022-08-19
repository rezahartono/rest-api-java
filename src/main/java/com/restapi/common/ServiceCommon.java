package com.restapi.common;

import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.restapi.bo.UserBO;
import com.restapi.vo.SettingConstant;

public class ServiceCommon {

	private static ServiceCommon _ServiceCommon;

	public static ServiceCommon getInstance() {
		_ServiceCommon = new ServiceCommon();

		return _ServiceCommon;
	}

	public String generateToken(int idUser, String sessionId) {
		String token = null;

		try {
			Algorithm algorithm = Algorithm.HMAC512(SettingConstant.JwtConstant.SECRET_CODE);

			token = JWT.create().withIssuer(SettingConstant.JwtConstant.AUTHOR).withClaim("user_id", idUser)
					.withClaim("session_id", sessionId).sign(algorithm);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return token;
	}

	public Map<String, String> decodeToken(String authToken) {
		Map<String, String> result = new HashMap<String, String>();

		String[] tokenData = authToken.split(" ", 2);

		String tokenType = tokenData[0];
		String token = tokenData[1];
		

		if (tokenType.equals(SettingConstant.JwtConstant.TOKEN_TYPE)) {
			try {
				Algorithm algorithm = Algorithm.HMAC512(SettingConstant.JwtConstant.SECRET_CODE);
				JWTVerifier verifier = JWT.require(algorithm).withIssuer(SettingConstant.JwtConstant.AUTHOR)
						.build();

				DecodedJWT data = verifier.verify(token);

				result.put("user_id", data.getClaim("user_id").toString().replaceAll("\"", ""));
				result.put("session_id", data.getClaim("session_id").toString().replaceAll("\"", ""));
				
				System.out.println(result);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			result = null;
		}

		return result;
	}
	
	public boolean isActiveSession(String userId, String sessionId) {
		boolean isActive = false;
		String oldSession = null;
		
		try {
			oldSession = UserBO.getInstance().sessionCheck(userId, sessionId);
			System.out.println("old session ==> "+oldSession);
			System.out.println("new session ==> "+sessionId);
			
			if(sessionId.equals(oldSession)) {
				isActive = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isActive;
	}
}
