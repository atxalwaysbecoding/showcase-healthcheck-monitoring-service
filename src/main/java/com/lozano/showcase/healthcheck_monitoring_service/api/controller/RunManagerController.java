package com.lozano.showcase.healthcheck_monitoring_service.api.controller;

import com.lozano.showcase.healthcheck_monitoring_service.domain.service.run.RunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/runManager")
public class RunManagerController {

    private RunManager runManager;

    @Autowired
    public RunManagerController(RunManager runManager) {
        this.runManager = runManager;
    }

    @GetMapping
    public ResponseEntity getRunStatus(){
        return ResponseEntity.ok(this.runManager.getRunState());
    }

    @PostMapping
    @RequestMapping(value = "/start")
    public ResponseEntity startRun(){
        return ResponseEntity.ok(this.runManager.startRunManager());
    }

    @PostMapping
    @RequestMapping(value = "/stop")
    public ResponseEntity stopRun(){
        return ResponseEntity.ok(this.runManager.stopRunManager());
    }

}
