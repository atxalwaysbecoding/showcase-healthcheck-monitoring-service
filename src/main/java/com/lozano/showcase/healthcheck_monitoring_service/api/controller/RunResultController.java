package com.lozano.showcase.healthcheck_monitoring_service.api.controller;

import com.lozano.showcase.healthcheck_monitoring_service.domain.service.runresult.RunResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/runResult")
public class RunResultController {

    private RunResultManager runResultManager;

    @Autowired
    public RunResultController(RunResultManager runResultManager) {
        this.runResultManager = runResultManager;
    }

    @GetMapping
    @RequestMapping(value = "/all/{id}")
    public ResponseEntity startRun(@PathVariable String id){
        return ResponseEntity.ok(this.runResultManager.getResultsByHealthCheckId(id));
    }
}
