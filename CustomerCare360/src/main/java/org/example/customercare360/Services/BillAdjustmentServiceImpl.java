package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.Bill;
import org.example.customercare360.Entity.BillAdjustment;
import org.example.customercare360.Enums.BillStatus;
import org.example.customercare360.Exception.AdjustmentAlreadyProcessedException;
import org.example.customercare360.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.customercare360.Repository.BillAdjustmentRepository;

import java.util.List;



@Service
public class BillAdjustmentServiceImpl implements BillAdjustmentService {



    @Autowired
    private BillAdjustmentRepository billAdjustmentRepository;

    public BillAdjustmentServiceImpl(
            BillAdjustmentRepository billAdjustmentRepository,
            BillRepository billRepository) {

        this.billAdjustmentRepository = billAdjustmentRepository;
        this.billRepository = billRepository;
    }










    @Override
    public List<BillAdjustmentResponse> getBillAdjustments(
            Integer billId) {

        return billAdjustmentRepository
                .findByBillId(billId)
                .stream()
                .map(billAdjustment -> new BillAdjustmentResponse(
                        billAdjustment.getAdjustmentId(),
                        billAdjustment.getStatus()
                ))
                .toList();
    }

    @Override
    public BillAdjustmentResponse createBillAdjustment(
            BillAdjustmentRequest request) {

        BillAdjustment billAdjustment = new BillAdjustment();

        billAdjustment.setBillId(
                request.getBillId());

        billAdjustment.setReason(
                request.getReason());

        billAdjustment.setAmountDelta(
                request.getAmountDelta());

        billAdjustment.setApprovedBy(
                request.getApprovedBy());

        billAdjustment.setStatus(
                "REQUESTED");

        BillAdjustment savedBillAdjustment =
                billAdjustmentRepository.save(
                        billAdjustment);

        return new BillAdjustmentResponse(
                savedBillAdjustment.getAdjustmentId(),
                savedBillAdjustment.getStatus()
        );
    }


    @Override
    public List<BillAdjustmentAdminViewResponse>
    getAllBillAdjustmentsForAdmin() {

        return billAdjustmentRepository.findAll()
                .stream()
                .map(billAdjustment ->
                        new BillAdjustmentAdminViewResponse(
                                billAdjustment.getAdjustmentId(),
                                billAdjustment.getBillId(),
                                billAdjustment.getReason(),
                                billAdjustment.getAmountDelta(),
                                billAdjustment.getApprovedBy(),
                                billAdjustment.getStatus(),
                                billAdjustment.getCreatedBy(),
                                billAdjustment.getModifiedBy()
                        ))
                .toList();
    }



    @Autowired
    private BillRepository billRepository;

    @Override
    public BillAdjustmentResponse updateBillAdjustment(
            Integer adjustmentId,
            BillAdjustmentUpdateRequest request) {

        System.out.println("Entered the billadjustment");

        BillAdjustment billAdjustment = billAdjustmentRepository
                .findById(adjustmentId)
                .orElseThrow();

        String currentStatus = billAdjustment.getStatus();

        // Only REQUESTED can be processed
        if ("APPROVED".equalsIgnoreCase(currentStatus)) {
            throw new AdjustmentAlreadyProcessedException(
                    "Adjustment request already approved");
        }

        if ("REJECTED".equalsIgnoreCase(currentStatus)) {
            throw new AdjustmentAlreadyProcessedException(
                    "Adjustment request already rejected");
        }




        billAdjustment.setStatus(request.getStatus());
        billAdjustment.setApprovedBy(request.getApprovedBy());

        if ("APPROVED".equalsIgnoreCase(request.getStatus())) {

            Bill bill = billRepository
                    .findById(billAdjustment.getBillId())
                    .orElseThrow();

            bill.setAmount(
                    bill.getAmount()
                            - billAdjustment.getAmountDelta());

            bill.setStatus(BillStatus.ADJUSTED);

            billRepository.save(bill);
        }

        BillAdjustment savedBillAdjustment =
                billAdjustmentRepository.save(billAdjustment);

        return new BillAdjustmentResponse(
                savedBillAdjustment.getAdjustmentId(),
                savedBillAdjustment.getStatus());
    }

}