package org.vdev.dto;

import javax.servlet.http.HttpServletRequest;

import org.vdev.utils.HttpUtils;

public class LogDTO extends BaseDTO {
	
	private String method;
	private String ipAddress;
	private String path;
	private String body;
	private int statusCode;
	private String role;
	private String device;
	private String parameters;
	
	public LogDTO() {
		super();
	}
	
	public LogDTO(HttpServletRequest request) {
		super();
		this.method = request.getMethod();
		this.ipAddress = HttpUtils.getRequestIP(request);
		this.path = request.getRequestURI().substring(request.getContextPath().length());
        this.device = request.getHeader("User-Agent");
        this.parameters = HttpUtils.getRequestParameter(request);
	}
	
	public LogDTO(String method, String ipAddress, String path, String body, int statusCode) {
		super();
		this.method = method;
		this.ipAddress = ipAddress;
		this.path = path;
		this.body = body;
		this.statusCode = statusCode;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	
}
