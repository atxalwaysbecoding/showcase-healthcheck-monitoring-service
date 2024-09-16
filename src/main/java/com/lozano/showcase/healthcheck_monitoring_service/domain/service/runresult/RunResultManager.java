package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;

public interface RunResultManager {

    void logRunResult(HealthCheckRunResponse healthCheckRunResponse);

}
