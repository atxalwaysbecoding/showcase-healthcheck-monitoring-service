package com.lozano.showcase.healthcheck_monitoring_service.domain.service.run;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.RunStateEnum;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.service.client.HealthCheckClient;
import com.lozano.showcase.healthcheck_monitoring_service.domain.service.healthcheck.HealthCheckManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RunManagerImpl implements RunManager{

    private HealthCheckClient healthCheckClient;
    private HealthCheckManager healthCheckManager;
    private RunStateEnum runState;

    @Autowired
    public RunManagerImpl(HealthCheckClient healthCheckClient, HealthCheckManager healthCheckManager) {
        this.healthCheckClient = healthCheckClient;
        this.healthCheckManager = healthCheckManager;
        this.runState = RunStateEnum.RUNNING;
    }


    @Override
    public boolean stopRunManager() {
        this.runState = RunStateEnum.STOPPED;
        return true;
    }

    @Override
    public boolean startRunManager() {
        this.runState = RunStateEnum.RUNNING;
        return true;
    }

    @Override
    public RunStateEnum getRunState() {
        return this.runState;
    }

    //10 seconds
    @Scheduled(fixedRateString = "${run.fixedRateInMillis}")
    private void run(){

        if (this.runState.equals(RunStateEnum.RUNNING)){
            log.info("Run manager run started...");
            for (HealthCheckEntity healthCheck : this.healthCheckManager.getAllHealthCheckByStatus(true)){
                log.info("Simulating run for HealthCheck ID {}", healthCheck.getId());
                this.healthCheckClient.executeHttpRequestAndGetResponse(healthCheck);
            }
        } else {
            log.warn("Run manager at stopped state - not running active HealthChecks.");
        }
    }
}
