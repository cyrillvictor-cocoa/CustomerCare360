package org.example.customercare360.Services;

import org.example.customercare360.DTO.FaqResponse;
import org.example.customercare360.Services.FAQService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQServiceImpl implements FAQService {

    @Override
    public List<FaqResponse> getFaqs() {
        return List.of(
                new FaqResponse(
                        "How can I raise a complaint?",
                        "Navigate to the Complaints section and submit your issue."
                ),
                new FaqResponse(
                        "How can I track my complaint status?",
                        "Navigate to My Complaints and view the current status."
                )
        );
    }
}