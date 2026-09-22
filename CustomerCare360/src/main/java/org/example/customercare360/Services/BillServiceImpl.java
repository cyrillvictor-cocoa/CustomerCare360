package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.Bill;
import org.example.customercare360.Enums.BillStatus;
import org.example.customercare360.Exception.BillNotFoundException;
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
    public String createBill(CreateBillRequest request) {

        Bill bill = new Bill();

        bill.setAccountId(request.getAccountId());
        bill.setCycleId(request.getCycleId());
        bill.setUsage(request.getUsage());

        int units = Integer.parseInt(request.getUsage());
        double ratePerUnit = 9.0;
        double amount = units * ratePerUnit;

        bill.setAmount(amount);
        bill.setDueDate(request.getDueDate());
        bill.setStatus(BillStatus.GENERATED);

        billRepository.save(bill);

        return "Bill Created Successfully";
    }

    @Override
    public List<BillResponse> getCustomerBills(Integer accountId) {

        List<Bill> bills = billRepository.findByAccountId(accountId);
        if(bills.isEmpty()) {
            throw new BillNotFoundException(
                    "No bills found for account id " + accountId);
        }

        List<BillResponse> responseList = new ArrayList<>();

        for(Bill bill : bills){

            BillResponse response = new BillResponse();

            response.setBillId(bill.getBillId());
            response.setAccountId(bill.getAccountId());
            response.setUsage(bill.getUsage());

            int units = Integer.parseInt(bill.getUsage());
            double ratePerUnit = 9.0;
            double amount = units * ratePerUnit;
            bill.setAmount(amount);

            //response.setAmount(bill.getAmount());
            response.setDueDate(bill.getDueDate());
            response.setStatus(bill.getStatus().name());
            responseList.add(response);
        }

        return responseList;
    }


    @Override
    public List<BillResponse> getAllBills() {

        List<Bill> bills = billRepository.findAll();

        List<BillResponse> response = new ArrayList<>();

        for (Bill bill : bills) {

            BillResponse dto = new BillResponse();

            dto.setBillId(bill.getBillId());
            dto.setAccountId(bill.getAccountId());
            //dto.setCycleId(bill.getCycleId());
            dto.setUsage(bill.getUsage());
            dto.setAmount(bill.getAmount());
            dto.setDueDate(bill.getDueDate());
            dto.setStatus(bill.getStatus().name());

            response.add(dto);
        }

        return response;
    }

    @Override
    public BillResponse getBillById(Integer billId) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bill not found with id " + billId));

        BillResponse dto = new BillResponse();

        dto.setBillId(bill.getBillId());
        dto.setAccountId(bill.getAccountId());
        //dto.setCycleId(bill.getCycleId());
        dto.setUsage(bill.getUsage());
        dto.setAmount(bill.getAmount());
        dto.setDueDate(bill.getDueDate());
        dto.setStatus(bill.getStatus().name());

        return dto;
    }

    @Override
    public String updateBill(
            Integer billId,
            UpdateBillRequest request) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() ->
                        new BillNotFoundException(
                                "Bill not found with id " + billId));

        bill.setUsage(request.getUsage());
        bill.setAmount(request.getAmount());
        bill.setDueDate(request.getDueDate());

        billRepository.save(bill);

        return "Bill updated successfully";
    }
}