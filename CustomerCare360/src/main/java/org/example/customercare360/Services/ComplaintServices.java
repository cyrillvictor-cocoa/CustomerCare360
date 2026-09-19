package org.example.customercare360.Services;

import org.example.customercare360.DTO.ComplaintRequest;
import org.example.customercare360.DTO.ComplaintResponse;

import java.util.List;

public interface ComplaintServices {

    List<ComplaintResponse> getComplaints();

    ComplaintResponse createComplaint(
            ComplaintRequest request
    );

    ComplaintResponse updateComplaintStatus(
            Integer complaintId,
            String status
    );
}
