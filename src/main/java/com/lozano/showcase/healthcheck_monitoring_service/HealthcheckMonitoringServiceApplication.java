package com.lozano.showcase.healthcheck_monitoring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HealthcheckMonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcheckMonitoringServiceApplication.class, args);
	}

}
