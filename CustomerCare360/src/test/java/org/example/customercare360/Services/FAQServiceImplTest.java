package org.example.customercare360.Services;

import org.example.customercare360.DTO.FaqResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FAQServiceImplTest {

    private FAQService faqService;

    @BeforeEach
    void setUp() {
        faqService = new FAQServiceImpl();
    }

    @Test
    void getFaqs_shouldReturnFaqList() {

        List<FaqResponse> result = faqService.getFaqs();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getFaqs_shouldReturnCorrectFirstFaq() {

        List<FaqResponse> result = faqService.getFaqs();

        assertEquals(
                "How can I raise a complaint?",
                result.get(0).getQuestion()
        );

        assertEquals(
                "Navigate to the Complaints section and submit your issue.",
                result.get(0).getAnswer()
        );
    }

    @Test
    void getFaqs_shouldReturnCorrectSecondFaq() {

        List<FaqResponse> result = faqService.getFaqs();

        assertEquals(
                "How can I track my complaint status?",
                result.get(1).getQuestion()
        );

        assertEquals(
                "Navigate to My Complaints and view the current status.",
                result.get(1).getAnswer()
        );
    }
}