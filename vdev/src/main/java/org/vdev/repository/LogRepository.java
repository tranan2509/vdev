package org.vdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdev.entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
