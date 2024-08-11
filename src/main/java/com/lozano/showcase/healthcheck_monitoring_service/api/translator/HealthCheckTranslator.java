package com.lozano.showcase.healthcheck_monitoring_service.api.translator;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.HealthCheck;
import com.lozano.showcase.healthcheck_monitoring_service.api.model.HealthCheckParam;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckParamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HealthCheckTranslator extends Translator <HealthCheck, HealthCheckEntity>{

    private HealthCheckParamTranslator healthCheckParamTranslator;

    @Autowired
    public HealthCheckTranslator(HealthCheckParamTranslator healthCheckParamTranslator) {
        this.healthCheckParamTranslator = healthCheckParamTranslator;
    }

    @Override
    public HealthCheck toApiModel(HealthCheckEntity domain) {
        HealthCheck healthCheck = new HealthCheck();
        healthCheck.setId(domain.getId());
        healthCheck.setUrl(domain.getUrl());
        healthCheck.setActive(domain.isActive());

        Set<HealthCheckParam> healthCheckParamSet = new HashSet<>();
        for (HealthCheckParamEntity healthCheckParamEntity : domain.getParams()){
            healthCheckParamSet.add(this.healthCheckParamTranslator.toApiModel(healthCheckParamEntity));
        }

        healthCheck.setParams(healthCheckParamSet);
        return healthCheck;
    }

    @Override
    public HealthCheckEntity toDomainModel(HealthCheck api) {
        HealthCheckEntity healthCheckEntity = new HealthCheckEntity();
        healthCheckEntity.setId(api.getId());
        healthCheckEntity.setUrl(api.getUrl());
        healthCheckEntity.setActive(api.isActive());

        Set<HealthCheckParamEntity> healthCheckParamEntities = new HashSet<>();
        for (HealthCheckParam healthCheckParam : api.getParams()){
            healthCheckParamEntities.add(this.healthCheckParamTranslator.toDomainModel(healthCheckParam));
        }

        healthCheckEntity.setParams(healthCheckParamEntities);
        return healthCheckEntity;
    }
}
