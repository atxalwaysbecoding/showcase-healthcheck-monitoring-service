package com.lozano.showcase.healthcheck_monitoring_service.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="RUN_RESULT")
@AllArgsConstructor
public class RunResultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "RUN_RESULT_ID")
    private String runResultID;

    @Column(name = "HEALTHCHECK_ID")
    private String healthCheckId;

    @Column(name = "REQUEST_URL")
    private String url;

    @Column(name = "HTTP_STATUS_CODE")
    private Integer httpStatusCode;

    @Column(name = "RESPONSE_BODY")
    private String responseBody;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "START_DATE_TIME")
    private LocalDateTime startDateTime;

    @Column(name = "DURATION_IN_MILLIS")
    private long durationInMillis;

    @Enumerated(EnumType.ORDINAL)
    private RunResultHealth health;

    public RunResultEntity(){}

    public RunResultEntity(String healthCheckId, String url, Integer httpStatusCode, String responseBody, String errorMessage, LocalDateTime startDateTime, long durationInMillis, RunResultHealth health) {
        this.healthCheckId = healthCheckId;
        this.url = url;
        this.httpStatusCode = httpStatusCode;
        this.responseBody = responseBody;
        this.errorMessage = errorMessage;
        this.startDateTime = startDateTime;
        this.durationInMillis = durationInMillis;
        this.health = health;
    }
}
