package org.example.customercare360.Repository;

import org.example.customercare360.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository
        extends JpaRepository<Bill,Integer> {

    List<Bill> findByAccountId(Integer accountId);
}
