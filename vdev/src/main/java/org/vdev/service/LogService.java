package org.vdev.service;

import java.util.List;

import org.vdev.dto.LogDTO;

public interface LogService {
	LogDTO save(LogDTO dto);
	boolean save(List<LogDTO> dtos);
}
