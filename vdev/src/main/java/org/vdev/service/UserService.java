package org.vdev.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.vdev.dto.UserDTO;

public interface UserService  extends UserDetailsService {

	UserDTO findOneByUsername(String username);
	boolean isEmpty();
	UserDTO save(UserDTO dto);
	boolean save(List<UserDTO> dtos);
}
