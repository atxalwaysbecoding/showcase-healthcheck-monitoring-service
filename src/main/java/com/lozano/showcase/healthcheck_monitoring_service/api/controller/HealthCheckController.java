package com.lozano.showcase.healthcheck_monitoring_service.api.controller;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.HealthCheck;
import com.lozano.showcase.healthcheck_monitoring_service.api.translator.HealthCheckTranslator;
import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import com.lozano.showcase.healthcheck_monitoring_service.domain.service.healthcheck.HealthCheckManager;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/healthcheck")
public class HealthCheckController {

    private HealthCheckTranslator healthCheckTranslator;
    private HealthCheckManager healthCheckManager;

    @Autowired
    public HealthCheckController(HealthCheckTranslator healthCheckTranslator, HealthCheckManager healthCheckManager) {
        this.healthCheckTranslator = healthCheckTranslator;
        this.healthCheckManager = healthCheckManager;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getHealthCheckByID(@PathVariable String id) throws Exception {
        if (!StringUtils.hasLength(id)){
            throw new Exception("Invalid HealthCheck ID");
        }
        return ResponseEntity.ok(this.healthCheckTranslator.toApiModel(this.healthCheckManager.getHealtCheckByID(id)));
    }

    @PostMapping
    public ResponseEntity createHealthCheck(@RequestBody HealthCheck healthCheck) throws Exception {
        if (healthCheck==null){
            throw new Exception("HealCheck cannot be null");
        }
        return ResponseEntity.ok(this.healthCheckTranslator.toApiModel(this.healthCheckManager.createHealthCheck(this.healthCheckTranslator.toDomainModel(healthCheck))));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateHealthCheck(@RequestBody HealthCheck healthCheck, @PathVariable String id) throws Exception {
        if (healthCheck==null){
            throw new Exception("HealCheck cannot be null");
        } else {
            if (!healthCheck.getId().equalsIgnoreCase(id)){
                throw new Exception("Provided HealthCheck ID does not match request body");
            }
        }
        return ResponseEntity.ok(this.healthCheckTranslator.toApiModel(this.healthCheckManager.updateHealthCheck(this.healthCheckTranslator.toDomainModel(healthCheck))));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteHealthCheck(@PathVariable String id) throws Exception {
        if (!StringUtils.hasLength(id)){
            throw new Exception("Invalid HealthCheck ID");
        }
        return ResponseEntity.ok(this.healthCheckManager.deleteHealthCheckById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllHealthCheckByActiveStatus(@RequestParam boolean isActive){
        List<HealthCheck> healthChecks = new ArrayList<>();
        for (HealthCheckEntity healthCheckEntity : this.healthCheckManager.getAllHealthCheckByStatus(isActive)){
            healthChecks.add(this.healthCheckTranslator.toApiModel(healthCheckEntity));
        }
        return ResponseEntity.ok(healthChecks);
    }
}
