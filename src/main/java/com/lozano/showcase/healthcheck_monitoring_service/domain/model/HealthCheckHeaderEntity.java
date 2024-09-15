package com.lozano.showcase.healthcheck_monitoring_service.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="HEALTHCHECK_HEADER")
public class HealthCheckHeaderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "HEADER_ID")
    private String headerID;

    @Column
    private String headerName;

    @Column
    private String headerValue;

}
