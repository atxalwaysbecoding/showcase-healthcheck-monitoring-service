package com.lozano.showcase.healthcheck_monitoring_service.domain.service.healthcheck;

import com.lozano.showcase.healthcheck_monitoring_service.domain.model.HealthCheckEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class HealthCheckManagerImpl implements HealthCheckManager {

    private HealthCheckRepository healthCheckRepository;

    @Autowired
    public HealthCheckManagerImpl(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    @Override
    public HealthCheckEntity getHealtCheckByID(String id) {
        Optional<HealthCheckEntity> entity = this.healthCheckRepository.findById(id);
        return entity.orElse(null);
    }

    @Override
    public HealthCheckEntity createHealthCheck(HealthCheckEntity healthCheckEntity) {
        return this.healthCheckRepository.save(healthCheckEntity);
    }

    @Override
    public HealthCheckEntity updateHealthCheck(HealthCheckEntity healthCheckEntity) {
        this.validateHealthCheckEntityForSave(healthCheckEntity);
        return this.healthCheckRepository.save(healthCheckEntity);
    }

    @Override
    public boolean deleteHealthCheckById(String id) {
        HealthCheckEntity healthCheckEntity = this.getHealtCheckByID(id);
        if (healthCheckEntity!=null){
            this.healthCheckRepository.deleteById(id);
            return true;
        }
        //todo - throw exception
        return false;
    }

    @Override
    public List<HealthCheckEntity> getAllHealthCheckByStatus(boolean isActive) {
        return this.healthCheckRepository.findByActiveTrue(isActive);
    }

    private void validateHealthCheckEntityForSave(HealthCheckEntity healthCheckEntity){
        //todo
    }

}
