package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingCycleRequest;
import org.example.customercare360.Entity.BillingCycle;
import org.example.customercare360.Enums.ServiceType;
import org.example.customercare360.Repository.BillingCycleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillingCycleServiceImplTest {

    @Mock
    private BillingCycleRepository billingCycleRepository;

    @InjectMocks
    private BillingCycleServiceImpl billingCycleService;

    @Test
    void testCreateCycle() {

        BillingCycle cycle = new BillingCycle();

        cycle.setCycleId(1);

        when(billingCycleRepository.save(any(BillingCycle.class)))
                .thenReturn(cycle);

        BillingCycleRequest request = new BillingCycleRequest();

        request.setServiceType(ServiceType.ELECTRICITY);

        request.setPeriodStart(LocalDateTime.now());

        request.setPeriodEnd(LocalDateTime.now().plusDays(30));

        BillingCycle result = billingCycleService.createCycle(request);

        assertEquals(1, result.getCycleId());
    }

    @Test
    void testGetAllCycles() {

        BillingCycle cycle = new BillingCycle();

        cycle.setCycleId(1);

        when(billingCycleRepository.findAll())
                .thenReturn(List.of(cycle));

        List<BillingCycle> result = billingCycleService.getAllCycles();

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateCycle() {

        BillingCycle cycle = new BillingCycle();

        cycle.setCycleId(1);

        when(billingCycleRepository.findById(1))
                .thenReturn(Optional.of(cycle));

        BillingCycleRequest request = new BillingCycleRequest();

        request.setServiceType(ServiceType.GAS);

        request.setPeriodStart(LocalDateTime.now());

        request.setPeriodEnd(LocalDateTime.now().plusDays(15));

        String result =
                billingCycleService.updateCycle(1, request);

        assertEquals(
                "Billing Cycle Updated Successfully", result);
    }

    @Test
    void testDeleteCycle() {

        doNothing().when(billingCycleRepository).deleteById(1);

        String result = billingCycleService.deleteCycle(1);

        assertEquals("Billing Cycle Deleted Successfully", result);
    }
}