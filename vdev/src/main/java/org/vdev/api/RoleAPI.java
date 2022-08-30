package org.vdev.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdev.api.response.BaseResponse;
import org.vdev.constants.Constants;
import org.vdev.dto.RoleDTO;
import org.vdev.service.RoleService;

@RestController
public class RoleAPI {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/api/info/role")
	public ResponseEntity<BaseResponse<List<RoleDTO>>> findAll() {
		List<RoleDTO> dtos = roleService.findAll();
		BaseResponse<List<RoleDTO>> response = new BaseResponse<List<RoleDTO>>(dtos, Constants.EMPTY);
		return ResponseEntity.ok(response);
	}
}
