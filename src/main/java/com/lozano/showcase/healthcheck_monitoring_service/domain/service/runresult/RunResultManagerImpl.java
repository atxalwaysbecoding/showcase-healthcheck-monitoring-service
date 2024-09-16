package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RunResultManagerImpl implements RunResultManager {

    @Override
    public void logRunResult(HealthCheckRunResponse healthCheckRunResponse) {
        log.info("RunResultManagerImpl - Logging run result for healthCheckId {}, statusCode {}", healthCheckRunResponse.getHealthCheckId(), healthCheckRunResponse.getHttpStatusCode());
    }
}
