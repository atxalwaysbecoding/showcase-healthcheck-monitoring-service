package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.RunResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RunResultRepository extends JpaRepository<RunResultEntity, String> {

    @Query(value = "SELECT * FROM RUN_RESULT rr WHERE rr.HEALTHCHECK_ID = ?1", nativeQuery = true)
    List<RunResultEntity> findAllByHealthCheckId(String healthCheckId);
}
