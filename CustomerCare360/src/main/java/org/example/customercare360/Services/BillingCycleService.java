package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingCycleRequest;
import org.example.customercare360.Entity.BillingCycle;

import java.util.List;

public interface BillingCycleService {

    BillingCycle createCycle(
            BillingCycleRequest request);

    List<BillingCycle> getAllCycles();

    String updateCycle(
            Integer cycleId,
            BillingCycleRequest request);

    String deleteCycle(
            Integer cycleId);
}