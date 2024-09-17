package com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.RunResultEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RunResultManagerImpl implements RunResultManager {

    private RunResultRepository runResultRepository;

    @Autowired
    public RunResultManagerImpl(RunResultRepository runResultRepository) {
        this.runResultRepository = runResultRepository;
    }

    @Override
    public void logRunResult(HealthCheckRunResponse response) {
        RunResultEntity runResultEntity = new RunResultEntity(response.getHealthCheckId(), response.getUrl(), response.getHttpStatusCode(), response.getResponseBody(), response.getErrorMessage(), response.getStartDateTime(), response.getDurationInMillis(), response.getHealth());
        this.runResultRepository.save(runResultEntity);
        log.info("RunResultManagerImpl - Logging run result for healthCheckId {}, statusCode {}", response.getHealthCheckId(), response.getHttpStatusCode());
    }

    @Override
    public List<RunResultEntity> getResultsByHealthCheckId(String healthCheckId) {
        return this.runResultRepository.findAllByHealthCheckId(healthCheckId);
    }
}
