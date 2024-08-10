package com.lozano.showcase.healthcheck_monitoring_service.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HealthCheckParam implements Serializable {

    private String paramID;

    private String paramName;

    private String paramValue;

}
