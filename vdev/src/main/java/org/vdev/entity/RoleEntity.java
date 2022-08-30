package org.vdev.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@OneToMany(mappedBy = "role")
	List<UserEntity> users = new ArrayList<>();

	public RoleEntity() {
		super();
	}

	public RoleEntity(String name, List<UserEntity> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
}
