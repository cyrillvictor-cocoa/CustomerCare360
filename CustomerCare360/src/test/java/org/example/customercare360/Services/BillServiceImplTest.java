package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillResponse;
import org.example.customercare360.DTO.CreateBillRequest;
import org.example.customercare360.DTO.UpdateBillRequest;
import org.example.customercare360.Entity.Bill;
import org.example.customercare360.Enums.BillStatus;
import org.example.customercare360.Repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Test
    void testCreateBill() {

        CreateBillRequest request =
                new CreateBillRequest();

        request.setAccountId(5);
        request.setCycleId(1);
        request.setUsage("170");
        request.setAmount(1000.50);
        request.setDueDate(LocalDateTime.now());

        Bill savedBill = new Bill();

        savedBill.setBillId(10);
        savedBill.setAccountId(5);
        savedBill.setCycleId(1);
        savedBill.setUsage("170");
        savedBill.setAmount(1000.50);
        savedBill.setStatus(BillStatus.GENERATED);

        when(billRepository.save(any(Bill.class)))
                .thenReturn(savedBill);

        String result =
                billService.createBill(request);

        assertEquals("Bill Created Successfully", result);

        verify(billRepository, times(1))
                .save(any(Bill.class));
    }

    @Test
    void testGetAllBills() {

        Bill bill = new Bill();

        bill.setBillId(1);
        bill.setAccountId(2);
        bill.setCycleId(10);
        bill.setUsage("150");
        bill.setAmount(1200.50);
        bill.setDueDate(LocalDateTime.now());
        bill.setStatus(BillStatus.GENERATED);

        when(billRepository.findAll())
                .thenReturn(List.of(bill));

        List<BillResponse> result = billService.getAllBills();

        assertEquals(1, result.size());

        assertEquals(1, result.get(0).getBillId());

        assertEquals(1200.50, result.get(0).getAmount());
    }

    @Test
    void testGetBillById() {

        Bill bill = new Bill();

        bill.setBillId(5);
        bill.setAccountId(2);
        bill.setCycleId(1);
        bill.setUsage("250");
        bill.setAmount(3000.0);
        bill.setDueDate(LocalDateTime.now());
        bill.setStatus(BillStatus.GENERATED);

        when(billRepository.findById(5))
                .thenReturn(Optional.of(bill));

        BillResponse response =
                billService.getBillById(5);

        assertEquals(
                5,
                response.getBillId()
        );

        assertEquals(
                3000.0,
                response.getAmount()
        );
    }

    @Test
    void testUpdateBill() {

        Bill bill = new Bill();

        bill.setBillId(1);

        when(billRepository.findById(1))
                .thenReturn(Optional.of(bill));

        when(billRepository.save(any(Bill.class)))
                .thenReturn(bill);

        UpdateBillRequest request =
                new UpdateBillRequest();

        request.setUsage("500");
        request.setAmount(2500.0);
        request.setDueDate(LocalDateTime.now());

        String result =
                billService.updateBill(
                        1,
                        request
                );

        assertEquals(
                "Bill updated successfully",
                result
        );
    }

    @Test
    void testGetCustomerBills() {

        Bill bill1 = new Bill();

        bill1.setBillId(2);
        bill1.setAccountId(2);
        bill1.setUsage("300");
        bill1.setAmount(3000.0);
        bill1.setDueDate(LocalDateTime.now());
        bill1.setStatus(BillStatus.GENERATED);

        Bill bill2 = new Bill();

        bill2.setBillId(6);
        bill2.setAccountId(2);
        bill2.setUsage("150");
        bill2.setAmount(1200.5);
        bill2.setDueDate(LocalDateTime.now());
        bill2.setStatus(BillStatus.GENERATED);

        when(billRepository.findByAccountId(2))
                .thenReturn(
                        List.of(bill1, bill2)
                );

        List<BillResponse> result =
                billService.getCustomerBills(2);

        assertEquals(
                2,
                result.size()
        );

        assertEquals(
                2,
                result.get(0).getBillId()
        );

        assertEquals(
                3000.0,
                result.get(0).getAmount()
        );
    }


}