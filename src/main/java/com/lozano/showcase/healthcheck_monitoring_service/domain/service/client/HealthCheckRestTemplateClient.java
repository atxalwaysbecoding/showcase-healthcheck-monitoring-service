package com.lozano.showcase.healthcheck_monitoring_service.domain.service.client;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckHeaderEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult.RunResultManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
    public boolean executeHttpRequestAndGetResponse(HealthCheckEntity healthCheckEntity) {

        ResponseEntity<String> response = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(healthCheckEntity.getUrl());

        if (healthCheckEntity.getParams()!=null && !healthCheckEntity.getParams().isEmpty()) {
            for (Map.Entry<String, String> entry : healthCheckEntity.convertParamEntityToURLParams().entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        HttpHeaders headers = new HttpHeaders();
        if (healthCheckEntity.getHeaders()!=null && !healthCheckEntity.getHeaders().isEmpty()){
            for (HealthCheckHeaderEntity healthCheckHeaderEntity : healthCheckEntity.getHeaders()){
                headers.set(healthCheckHeaderEntity.getHeaderName(), healthCheckHeaderEntity.getHeaderValue());
            }
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try{

            response = this.restTemplate.exchange(builder.toUriString(), HttpMethod.valueOf(healthCheckEntity.getHttpMethod()), requestEntity, String.class);

        } catch (Exception e){
            log.error("HealthCheckRestTemplateClient.executeHttpRequestAndGetResponse exception message: {}", e.getMessage());
        }

        if (response!=null){
            this.runResultManager.logRunResult(healthCheckEntity.getId(), response.getStatusCode().value(), response.getBody());
        } else {
            log.error("HealthCheckRestTemplateClient.executeHttpRequestAndGetResponse response null for healthCheckEntity ID {}", healthCheckEntity.getId());
            return false;
        }
        return true;
    }

}
