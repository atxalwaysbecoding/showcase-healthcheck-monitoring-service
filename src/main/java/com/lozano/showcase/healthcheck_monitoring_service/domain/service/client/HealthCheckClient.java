package com.lozano.showcase.healthcheck_monitoring_service.domain.service.client;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;
import org.springframework.http.ResponseEntity;

public interface HealthCheckClient {

    HealthCheckRunResponse executeHttpRequestAndGetResponse(HealthCheckEntity healthCheckEntity);

}
