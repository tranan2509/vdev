package org.vdev.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false)
	private String username; 
	@Column(nullable = false)
	private String password;

	private String token;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;
	
	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password, String token, RoleEntity role) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
		this.role = role;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	
}
