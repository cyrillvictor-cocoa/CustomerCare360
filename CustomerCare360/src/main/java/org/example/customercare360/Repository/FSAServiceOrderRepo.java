package org.example.customercare360.Repository;

import org.example.customercare360.Entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FSAServiceOrderRepo
        extends JpaRepository<ServiceOrder, Integer> {

    List<ServiceOrder> findByAssignedTo(Integer assignedTo);

}