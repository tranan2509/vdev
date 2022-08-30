package org.vdev.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdev.api.response.BaseResponse;
import org.vdev.constants.Constants;
import org.vdev.dto.RoleDTO;
import org.vdev.dto.UserDTO;
import org.vdev.service.RoleService;
import org.vdev.service.UserService;

@RestController
public class InitializationAPI {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/initialization")
	public ResponseEntity<BaseResponse<Boolean>> initialization(){
		BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
		baseResponse.setObject(true);
		baseResponse.setSuccess(true);
		boolean hasChanged = false;
		try {
			if (roleService.isEmpty()) {
				List<RoleDTO> dtos = new ArrayList<RoleDTO>();
				dtos.add(new RoleDTO(Constants.ROLE.ROLE_ADMIN, Constants.ROLE.ADMIN));
				dtos.add(new RoleDTO(Constants.ROLE.ROLE_STAFF, Constants.ROLE.STAFF));
				dtos.add(new RoleDTO(Constants.ROLE.ROLE_USER, Constants.ROLE.USER));
				roleService.save(dtos);
				hasChanged = true;
			}
			
			if (userService.isEmpty()) {
				List<UserDTO> dtos = new ArrayList<UserDTO>();
				dtos.add(new UserDTO("none", "none", "", Constants.ROLE.USER));
				dtos.add(new UserDTO("admin", "admin", "", Constants.ROLE.ADMIN));
				dtos.add(new UserDTO("staff", "staff", "", Constants.ROLE.STAFF));
				dtos.add(new UserDTO("user", "user", "", Constants.ROLE.USER));
				userService.save(dtos);
				hasChanged = true;
			}
			
			if (!hasChanged) {
				baseResponse.setMessage("Dữ liệu đã được khởi tạo trước đó");
			} else {
				baseResponse.setMessage("Dữ liệu đã được khởi tạo thành công");
			}
		} catch (Exception e) {
			baseResponse = new BaseResponse<Boolean>(false, false, "Khởi tạo không thành công");
		}
		
		return new ResponseEntity<BaseResponse<Boolean>>(baseResponse, HttpStatus.OK);
	}
}
