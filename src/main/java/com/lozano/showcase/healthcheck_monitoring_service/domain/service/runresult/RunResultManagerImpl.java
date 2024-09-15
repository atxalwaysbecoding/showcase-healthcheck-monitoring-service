package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RunResultManagerImpl implements RunResultManager {


    @Override
    public boolean logRunResult(String healthCheckId, int httpStatusCode, String body) {
        log.info("RunResultManagerImpl - Logging run result for healthCheckId {}, statusCode {}", healthCheckId, httpStatusCode);
        return true;
    }
}
