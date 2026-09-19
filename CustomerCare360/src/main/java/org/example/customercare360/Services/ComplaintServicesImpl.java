package org.example.customercare360.Services;

import org.example.customercare360.DTO.ComplaintRequest;
import org.example.customercare360.DTO.ComplaintResponse;
import org.example.customercare360.Entity.Complaint;
import org.example.customercare360.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class ComplaintServicesImpl implements ComplaintServices {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public List<ComplaintResponse> getComplaints() {


        return complaintRepository.findAll()
                .stream()
                .map(complaint -> new ComplaintResponse(
                        complaint.getComplaintId(),
                        complaint.getStatus()
                ))
                .toList();
    }

    @Override
    public ComplaintResponse createComplaint(
            ComplaintRequest request) {

        Complaint complaint = new Complaint();

        complaint.setCustomerId(
                request.getCustomerId());

        complaint.setCategory(
                request.getCategory());

        complaint.setDescription(
                request.getDescription());

        complaint.setLoggedDate(
                LocalDateTime.now());

        complaint.setStatus(
                "OPEN");



        Complaint savedComplaint =
                complaintRepository.save(
                        complaint);

        return new ComplaintResponse(
                savedComplaint.getComplaintId(),
                savedComplaint.getStatus()
        );
    }

    @Override
    public ComplaintResponse updateComplaintStatus(
            Integer complaintId,
            String status) {

        Complaint complaint = complaintRepository
                .findById(complaintId)
                .orElseThrow(() ->
                        new RuntimeException("Complaint not found"));

        complaint.setStatus(status);

        Complaint updatedComplaint =
                complaintRepository.save(complaint);

        return new ComplaintResponse(
                updatedComplaint.getComplaintId(),
                updatedComplaint.getStatus()
        );
    }
}
