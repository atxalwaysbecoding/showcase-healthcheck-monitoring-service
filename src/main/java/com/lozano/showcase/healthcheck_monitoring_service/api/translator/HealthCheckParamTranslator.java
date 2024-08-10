package com.lozano.showcase.healthcheck_monitoring_service.api.translator;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.HealthCheckParam;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckParamEntity;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckParamTranslator extends Translator <HealthCheckParam, HealthCheckParamEntity>{

    @Override
    public HealthCheckParam toApiModel(HealthCheckParamEntity domain) {

        HealthCheckParam healthCheckParam = new HealthCheckParam();
        healthCheckParam.setParamID(domain.getParamID());
        healthCheckParam.setParamName(domain.getParamName());
        healthCheckParam.setParamValue(domain.getParamValue());
        return healthCheckParam;
    }

    @Override
    public HealthCheckParamEntity toDomainModel(HealthCheckParam api) {

        HealthCheckParamEntity healthCheckParamEntity = new HealthCheckParamEntity();
        healthCheckParamEntity.setParamID(api.getParamID());
        healthCheckParamEntity.setParamName(api.getParamName());
        healthCheckParamEntity.setParamValue(api.getParamValue());
        return healthCheckParamEntity;
    }
}
