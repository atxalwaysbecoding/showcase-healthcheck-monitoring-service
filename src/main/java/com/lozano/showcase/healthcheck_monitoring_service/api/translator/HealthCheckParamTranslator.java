package com.lozano.showcase.healthcheck_monitoring_service.api.translator;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.KeyValuePair;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckParamEntity;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckParamTranslator extends Translator <KeyValuePair, HealthCheckParamEntity>{

    @Override
    public KeyValuePair toApiModel(HealthCheckParamEntity domain) {

        KeyValuePair keyValuePair = new KeyValuePair();
        keyValuePair.setId(domain.getParamID());
        keyValuePair.setKey(domain.getParamName());
        keyValuePair.setValue(domain.getParamValue());
        return keyValuePair;
    }

    @Override
    public HealthCheckParamEntity toDomainModel(KeyValuePair api) {

        HealthCheckParamEntity healthCheckParamEntity = new HealthCheckParamEntity();
        healthCheckParamEntity.setParamID(api.getId());
        healthCheckParamEntity.setParamName(api.getKey());
        healthCheckParamEntity.setParamValue(api.getValue());
        return healthCheckParamEntity;
    }
}
