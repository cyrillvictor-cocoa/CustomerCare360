package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillResponse;
import org.example.customercare360.DTO.CreateBillRequest;
import org.example.customercare360.DTO.UpdateBillRequest;

import java.util.List;

public interface BillService {

    String createBill(CreateBillRequest request);

    List<BillResponse> getCustomerBills(Integer customerId);

    List<BillResponse> getAllBills();

    String updateBill(Integer billId,
                      UpdateBillRequest request);
}