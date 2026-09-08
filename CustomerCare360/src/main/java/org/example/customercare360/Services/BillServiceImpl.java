package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.Bill;
import org.example.customercare360.Repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository){
        this.billRepository = billRepository;
    }

    @Override
    public List<BillResponse> getCustomerBills(Integer customerId) {

        List<Bill> bills = billRepository.findAll();

        List<BillResponse> responseList =
                new ArrayList<>();

        for(Bill bill : bills){

            BillResponse response =
                    new BillResponse();

            response.setBillId(
                    bill.getBillId());

            response.setAmount(
                    bill.getAmount());

            response.setStatus(
                    bill.getStatus().name());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public DownloadBillResponse downloadBill(
            Integer billId) {

//        Bill bill=
//                billRepository.findById(billId)
//                        .orElseThrow();
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bill not found with id "
                                        + billId));

        DownloadBillResponse response =
                new DownloadBillResponse();

        response.setBillId(billId);

        response.setFileName(
                "Bill_"+billId+".pdf"
        );

        response.setDownloadUrl(
                "/documents/bills/Bill_"
                        +billId+".pdf"
        );

        return response;
    }

    @Override
    public List<BillResponse> getAllBills() {

        List<Bill> bills =
                billRepository.findAll();

        List<BillResponse> response =
                new ArrayList<>();

        for(Bill bill:bills){

            BillResponse dto =
                    new BillResponse();

            dto.setBillId(
                    bill.getBillId());

            dto.setAmount(
                    bill.getAmount());

            dto.setStatus(
                    bill.getStatus().name());

            response.add(dto);
        }

        return response;
    }

    @Override
    public String updateBill(
            Integer billId,
            UpdateBillRequest request) {

        Bill bill =
                billRepository.findById(billId)
                        .orElseThrow();

        bill.setUsage(
                request.getUsage());

        bill.setAmount(
                request.getAmount());

        bill.setDueDate(
                request.getDueDate());

        billRepository.save(bill);

        return "Bill updated successfully";
    }
}