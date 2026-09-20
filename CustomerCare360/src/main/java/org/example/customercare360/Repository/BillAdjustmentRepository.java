package org.example.customercare360.Repository;

import org.example.customercare360.Entity.BillAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillAdjustmentRepository
        extends JpaRepository<BillAdjustment, Integer> {

    List<BillAdjustment> findByBillId(Integer billId);
}