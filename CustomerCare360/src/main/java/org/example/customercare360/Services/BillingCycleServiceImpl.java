package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingCycleRequest;
import org.example.customercare360.Entity.BillingCycle;
import org.example.customercare360.Enums.ServiceType;
import org.example.customercare360.Repository.BillingCycleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingCycleServiceImpl
        implements BillingCycleService {

    private final BillingCycleRepository repository;

    public BillingCycleServiceImpl(
            BillingCycleRepository repository){

        this.repository = repository;
    }

    @Override
    public BillingCycle createCycle(
            BillingCycleRequest request){

        BillingCycle cycle = new BillingCycle();

        cycle.setServiceType(
                request.getServiceType());

        cycle.setPeriodStart(
                request.getPeriodStart());

        cycle.setPeriodEnd(
                request.getPeriodEnd());

        return repository.save(cycle);
    }

    @Override
    public List<BillingCycle> getAllCycles(){

        return repository.findAll();
    }

    @Override
    public String updateCycle(
            Integer cycleId,
            BillingCycleRequest request){

        BillingCycle cycle =
                repository.findById(cycleId)
                        .orElseThrow();

        cycle.setServiceType(
                request.getServiceType());

        cycle.setPeriodStart(
                request.getPeriodStart());

        cycle.setPeriodEnd(
                request.getPeriodEnd());

        repository.save(cycle);

        return "Billing Cycle Updated Successfully";
    }

    @Override
    public String deleteCycle(
            Integer cycleId){

        repository.deleteById(cycleId);

        return "Billing Cycle Deleted Successfully";
    }
}