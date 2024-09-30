package com.lozano.showcase.healthcheck_monitoring_service.domain.service.client;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckHeaderEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckRunResponse;
import com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult.RunResultManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@ConditionalOnProperty(name = "healthcheckclienttype", havingValue = "rest")
public class HealthCheckRestTemplateClient implements HealthCheckClient {

    private RunResultManager runResultManager;

    private RestTemplate restTemplate;

    @Autowired
    public HealthCheckRestTemplateClient(RunResultManager runResultManager) {
        this.runResultManager = runResultManager;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public HealthCheckRunResponse executeHttpRequestAndGetResponse(HealthCheckEntity healthCheckEntity) {

        Exception exception = null;
        ResponseEntity<String> response = null;
        UriComponentsBuilder builder = this.buildUriWithParams(healthCheckEntity);
        HttpEntity<String> requestEntity = new HttpEntity<>(this.createHeaders(healthCheckEntity));
        LocalDateTime startDateTime = LocalDateTime.now();

        try {
            response = this.restTemplate.exchange(builder.toUriString(), HttpMethod.valueOf(healthCheckEntity.getHttpMethod()), requestEntity, String.class);

        } catch (HttpClientErrorException ex){
            exception = ex;
            log.debug("HealthCheckRestTemplateClient.HttpClientErrorException for HC ID: '{}' - ex message: '{}'", healthCheckEntity.getId(), ex.getMessage());

        } catch (Exception e){
            log.debug("HealthCheckRestTemplateClient.Exception for HC ID: '{}' - e message: '{}'", healthCheckEntity.getId(), e.getMessage());
            exception = e;

        }
        if (exception!=null){
            return new HealthCheckRunResponse(healthCheckEntity.getId(), builder.toUriString(), null, null, exception.getMessage(), startDateTime, this.calculateDuration(startDateTime), null);
        } else {
            return new HealthCheckRunResponse(healthCheckEntity.getId(), builder.toUriString(), response.getStatusCode().value(), response.getBody(), null, startDateTime, this.calculateDuration(startDateTime), null);
        }

    }

    private UriComponentsBuilder buildUriWithParams(HealthCheckEntity healthCheckEntity){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(healthCheckEntity.getUrl());

        if (healthCheckEntity.getParams()!=null && !healthCheckEntity.getParams().isEmpty()) {
            for (Map.Entry<String, String> entry : healthCheckEntity.convertParamEntityToURLParams().entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder;
    }

    private HttpHeaders createHeaders(HealthCheckEntity healthCheckEntity){
        HttpHeaders headers = new HttpHeaders();
        if (healthCheckEntity.getHeaders()!=null && !healthCheckEntity.getHeaders().isEmpty()){
            for (HealthCheckHeaderEntity healthCheckHeaderEntity : healthCheckEntity.getHeaders()){
                headers.set(healthCheckHeaderEntity.getHeaderName(), healthCheckHeaderEntity.getHeaderValue());
            }
        }
        return headers;
    }

    private long calculateDuration(LocalDateTime startDateTime){
        LocalDateTime endDateTime = LocalDateTime.now();
        return ChronoUnit.NANOS.between(startDateTime, endDateTime);
    }
}
