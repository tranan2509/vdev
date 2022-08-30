package org.vdev.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vdev.dto.LogDTO;
import org.vdev.entity.LogEntity;
import org.vdev.repository.LogRepository;
import org.vdev.service.LogService;
import org.vdev.utils.CommonUtils;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private CommonUtils commonUtils;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private LogRepository logRepository;
	
	@Override
	public LogDTO save(LogDTO dto) {
		try {
			LogEntity entity = mapper.map(dto, LogEntity.class);
			logRepository.save(entity);
			return mapper.map(entity, LogDTO.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean save(List<LogDTO> dtos) {
		try {
			List<LogEntity> entities = commonUtils.mapper(dtos, LogEntity.class);
			logRepository.save(entities);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
