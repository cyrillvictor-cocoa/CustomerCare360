package org.example.customercare360.Repository;

import org.example.customercare360.Entity.ServiceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAccountRepository extends JpaRepository<ServiceAccount, Integer> {
}