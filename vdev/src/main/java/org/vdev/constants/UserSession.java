package org.vdev.constants;

import javax.servlet.http.HttpServletRequest;

import org.vdev.dto.UserDTO;

public class UserSession {
	
	public static UserDTO userInfo;
	
	public static UserDTO getUserInfo() {
		return userInfo;
	}
	
	public static void setUserInfo(UserDTO dto) {
		userInfo = dto;
	}
	
	public static UserDTO getUserInfo(HttpServletRequest request) {
		return (UserDTO)request.getSession().getAttribute(Constants.USER_INFO);
	}
	
	public static void setUserInfo(UserDTO dto, HttpServletRequest request) {
		request.getSession().setAttribute(Constants.USER_INFO, dto);
		setUserInfo(dto);
	}
}
