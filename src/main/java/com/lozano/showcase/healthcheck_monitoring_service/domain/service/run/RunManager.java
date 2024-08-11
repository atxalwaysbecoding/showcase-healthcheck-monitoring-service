package com.lozano.showcase.healthcheck_monitoring_service.domain.service.run;

import com.lozano.showcase.healthcheck_monitoring_service.api.model.RunStateEnum;

public interface RunManager {

    boolean stopRunManager();

    boolean startRunManager();

    RunStateEnum getRunState();
}
