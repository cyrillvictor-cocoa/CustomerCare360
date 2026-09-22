package org.example.customercare360.Controller;

import org.example.customercare360.DTO.CreateBillRequest;
import org.example.customercare360.Services.BillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AdminBillControllerTest {

    @Mock
    BillService billService;

    @InjectMocks
    AdminBillController controller;

    @Test
    void testCreateBill() {

        CreateBillRequest request = new CreateBillRequest();
        request.setAccountId(101);
        request.setUsage("200");

        when(billService.createBill(request))
                .thenReturn("Bill created successfully");

        String response = controller.createBill(request);

        assertEquals("Bill created successfully", response);
    }
}
