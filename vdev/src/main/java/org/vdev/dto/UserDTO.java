package org.vdev.dto;

public class UserDTO extends BaseDTO {
	private String username; 
	private String password;
	
	private String token;
	
	private String roleName;

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String password, String token, String roleName) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
		this.roleName = roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
