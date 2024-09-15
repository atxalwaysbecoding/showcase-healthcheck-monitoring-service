package com.lozano.showcase.healthcheck_monitoring_service.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeyValuePair implements Serializable {

    private String id;

    private String key;

    private String value;

}
