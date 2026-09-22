package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingCycleRequest;
import org.example.customercare360.Entity.BillingCycle;
import org.example.customercare360.Enums.ServiceType;
import org.example.customercare360.Exception.BillingCycleNotFoundException;
import org.example.customercare360.Exception.InvalidAccountException;
import org.example.customercare360.Repository.BillingCycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BillingCycleServiceImpl implements BillingCycleService {

    private final BillingCycleRepository repository;

    public BillingCycleServiceImpl(BillingCycleRepository repository){
        this.repository = repository;
    }

    @Override
    public BillingCycle createCycle(
            BillingCycleRequest request){

        BillingCycle cycle = new BillingCycle();

        cycle.setServiceType(request.getServiceType());
        cycle.setPeriodStart(request.getPeriodStart());
        cycle.setPeriodEnd(request.getPeriodEnd());

//        if(!serviceAccountRepository.existsById(
//                request.getAccountId())) {
//
//            throw new InvalidAccountException(
//                    "Account Id does not exist"
//            );
//        }

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

        BillingCycle cycle = repository.findById(cycleId)
                .orElseThrow(() ->
                        new BillingCycleNotFoundException(
                                "Billing Cycle not found with id " + cycleId));

        cycle.setServiceType(request.getServiceType());
        cycle.setPeriodStart(request.getPeriodStart());
        cycle.setPeriodEnd(request.getPeriodEnd());

        repository.save(cycle);

        return "Billing Cycle Updated Successfully";
    }

    @Override
    public String deleteCycle(
            Integer cycleId){

        if(!repository.existsById(cycleId)) {
            throw new BillingCycleNotFoundException(
                    "Billing Cycle not found with id " + cycleId);
        }

        repository.deleteById(cycleId);

        return "Billing Cycle Deleted Successfully";
    }
}