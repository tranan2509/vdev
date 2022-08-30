package org.vdev.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vdev.constants.Constants;
import org.vdev.dto.UserDTO;
import org.vdev.entity.RoleEntity;
import org.vdev.entity.UserEntity;
import org.vdev.repository.RoleRepository;
import org.vdev.repository.UserRepository;
import org.vdev.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUsername(username);
		 if (userEntity == null){
	            throw new UsernameNotFoundException("User does not exist!!!");
	        }
		Set<GrantedAuthority> auth = new HashSet<>();
		auth.add(new SimpleGrantedAuthority(userEntity.getRole().getCode()));
		return new User(userEntity.getUsername(), userEntity.getPassword(), auth);
	}
	
	@Override
	public UserDTO findOneByUsername(String username) {
		UserEntity entity = userRepository.findOneByUsername(username);
		if (entity != null) {
			return mapper.map(entity, UserDTO.class);
		}
		return null;
	}
	
	@Override
	public UserDTO save(UserDTO dto) {
		try {
			UserEntity entity = new UserEntity();
			if (dto.getId() == null) {
				dto.setPassword(passwordEncoder.encode(dto.getPassword()));
				entity = mapper.map(dto, UserEntity.class);
				RoleEntity role = roleRepository.findByName(dto.getRoleName());
				entity.setRole(role);
			} else {
				entity = userRepository.findOne(dto.getId());
				// TODO: UPDATE INFO
			}
			entity = userRepository.save(entity);
			return mapper.map(entity, UserDTO.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean save(List<UserDTO> dtos) {
		try {
			for (UserDTO dto : dtos) {
				save(dto);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isEmpty() {
		return userRepository.count() == Constants.DEFEAT;
	}


}
