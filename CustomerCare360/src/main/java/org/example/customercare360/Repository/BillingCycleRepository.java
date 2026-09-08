package org.example.customercare360.Repository;

import org.example.customercare360.Entity.BillingCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingCycleRepository
        extends JpaRepository<BillingCycle,Integer> {
}
