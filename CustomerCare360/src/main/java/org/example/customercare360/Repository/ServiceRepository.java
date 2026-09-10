package org.example.customercare360.Repository;

import org.example.customercare360.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository
        extends JpaRepository<ServiceEntity, Integer> {

    List<ServiceEntity>
    findByServiceName(String serviceName);
}