package com.lozano.showcase.healthcheck_monitoring_service.api.model;

import lombok.Data;

import java.util.Set;

@Data
public class HealthCheck {

    private String id;

    private String url;

    private Set<HealthCheckParam> params;
}
