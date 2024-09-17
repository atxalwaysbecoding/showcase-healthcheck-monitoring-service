package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.RunResultEntity;

import java.util.List;

public interface RunResultManager {

    void logRunResult(HealthCheckRunResponse healthCheckRunResponse);

    List<RunResultEntity> getResultsByHealthCheckId(String healthCheckId);

}
