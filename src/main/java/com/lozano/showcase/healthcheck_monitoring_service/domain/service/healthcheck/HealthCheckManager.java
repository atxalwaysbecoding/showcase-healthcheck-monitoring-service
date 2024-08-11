package com.lozano.showcase.healthcheck_monitoring_service.domain.service.healthcheck;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;

import java.util.List;

public interface HealthCheckManager {

    HealthCheckEntity getHealtCheckByID(String id);

    HealthCheckEntity createHealthCheck(HealthCheckEntity healthCheckEntity);

    HealthCheckEntity updateHealthCheck(HealthCheckEntity healthCheckEntity);

    boolean deleteHealthCheckById(String id);

    List<HealthCheckEntity> getAllHealthCheckByStatus(boolean isActive);

}
