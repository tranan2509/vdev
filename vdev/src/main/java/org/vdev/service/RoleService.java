package org.vdev.service;

import java.util.List;

import org.vdev.dto.RoleDTO;

public interface RoleService {

	public RoleDTO save(RoleDTO dto);
	public boolean save(List<RoleDTO> dtos);
	public boolean isEmpty();
	public List<RoleDTO> findAll();
}
