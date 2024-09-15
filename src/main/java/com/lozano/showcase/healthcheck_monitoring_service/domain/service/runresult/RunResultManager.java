package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

public interface RunResultManager {

    boolean logRunResult(String healthCheckId, int httpStatusCode, String body);

}
