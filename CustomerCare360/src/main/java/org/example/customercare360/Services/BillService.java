package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillResponse;
import org.example.customercare360.DTO.DownloadBillResponse;
import org.example.customercare360.DTO.UpdateBillRequest;

import java.util.List;

public interface BillService {

    List<BillResponse> getCustomerBills(Integer customerId);

    DownloadBillResponse downloadBill(Integer billId);

    List<BillResponse> getAllBills();

    String updateBill(Integer billId,
                      UpdateBillRequest request);
}