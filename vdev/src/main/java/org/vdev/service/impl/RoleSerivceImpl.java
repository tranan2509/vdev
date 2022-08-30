package org.vdev.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vdev.constants.Constants;
import org.vdev.dto.RoleDTO;
import org.vdev.entity.RoleEntity;
import org.vdev.repository.RoleRepository;
import org.vdev.service.RoleService;
import org.vdev.utils.CommonUtils;

@Service
public class RoleSerivceImpl implements RoleService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private CommonUtils commonUtils;
	
	@Override
	public RoleDTO save(RoleDTO dto) {
		try 
		{
			RoleEntity entity = mapper.map(dto, RoleEntity.class);
			roleRepository.save(entity);
			return mapper.map(entity, RoleDTO.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean save(List<RoleDTO> dtos) {
		try {
			List<RoleEntity> entities = commonUtils.mapper(dtos, RoleEntity.class);
			roleRepository.save(entities);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return roleRepository.count() == Constants.DEFEAT;
	}

	@Override
	public List<RoleDTO> findAll() {
		try {
			List<RoleEntity> entities = roleRepository.findAll();
			List<RoleDTO> dtos = commonUtils.mapper(entities, RoleDTO.class);
			return dtos;
		} catch (Exception e) {
			return null;
		}
	}
}
