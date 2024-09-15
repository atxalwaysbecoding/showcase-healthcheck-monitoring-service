package com.lozano.showcase.healthcheck_monitoring_service.domain.service.client;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;

public interface HealthCheckClient {

    boolean executeHttpRequestAndGetResponse(HealthCheckEntity healthCheckEntity);

}
