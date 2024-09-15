package com.lozano.showcase.healthcheck_monitoring_service.api.translator;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.KeyValuePair;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckHeaderEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckParamEntity;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckHeaderTranslator extends Translator <KeyValuePair, HealthCheckHeaderEntity>{

    @Override
    public KeyValuePair toApiModel(HealthCheckHeaderEntity domain) {

        KeyValuePair keyValuePair = new KeyValuePair();
        keyValuePair.setId(domain.getHeaderID());
        keyValuePair.setKey(domain.getHeaderName());
        keyValuePair.setValue(domain.getHeaderValue());
        return keyValuePair;
    }

    @Override
    public HealthCheckHeaderEntity toDomainModel(KeyValuePair api) {

        HealthCheckHeaderEntity healthCheckHeaderEntity = new HealthCheckHeaderEntity();
        healthCheckHeaderEntity.setHeaderID(api.getId());
        healthCheckHeaderEntity.setHeaderName(api.getKey());
        healthCheckHeaderEntity.setHeaderValue(api.getValue());
        return healthCheckHeaderEntity;
    }
}
