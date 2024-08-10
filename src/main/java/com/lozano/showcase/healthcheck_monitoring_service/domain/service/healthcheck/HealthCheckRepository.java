package com.lozano.showcase.healthcheck_monitoring_service.domain.service.healthcheck;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface HealthCheckRepository extends JpaRepository<HealthCheckEntity, String> {

}
