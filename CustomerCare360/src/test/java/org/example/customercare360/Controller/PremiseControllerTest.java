package org.example.customercare360.Controller;

import org.example.customercare360.DTO.PremiseRequest;
import org.example.customercare360.DTO.PremiseResponse;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Services.PremiseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PremiseControllerTest {

    @Mock
    private PremiseService premiseService;

    @InjectMocks
    private PremiseController premiseController;

    @Test
    void testGetAllPremises() {

        List<Premise> premises = new ArrayList<>();

        Premise premise = new Premise();
        premise.setPremiseId(1);

        premises.add(premise);

        when(premiseService.getAllPremises())
                .thenReturn(premises);

        List<Premise> result =
                premiseController.getAllPremises();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(premiseService, times(1))
                .getAllPremises();
    }

    @Test
    void testGetPremiseById() {

        PremiseResponse response =
                new PremiseResponse();

        response.setAddress("Anna Nagar");

        when(premiseService.getPremiseDetails(1))
                .thenReturn(response);

        PremiseResponse result =
                premiseController.getPremiseDetails(1);

        assertNotNull(result);
        assertEquals("Anna Nagar",
                result.getAddress());

        verify(premiseService, times(1))
                .getPremiseDetails(1);
    }

    @Test
    void testCreatePremise() {

        PremiseRequest request =
                new PremiseRequest();

        request.setAddress("Chennai");
        request.setRegion("North");
        request.setMeterId(1001);

        PremiseResponse response =
                new PremiseResponse();

        response.setAddress("Chennai");

        when(premiseService.createPremise(
                any(PremiseRequest.class)))
                .thenReturn(response);

        PremiseResponse result =
                premiseController.createPremise(
                        request);

        assertNotNull(result);
        assertEquals("Chennai",
                result.getAddress());

        verify(premiseService, times(1))
                .createPremise(any(PremiseRequest.class));
    }

    @Test
    void testUpdatePremise() {

        PremiseRequest request =
                new PremiseRequest();

        request.setAddress("Updated Chennai");
        request.setRegion("South");
        request.setMeterId(1002);

        PremiseResponse response =
                new PremiseResponse();

        response.setAddress("Updated Chennai");

        when(premiseService.updatePremise(
                eq(1),
                any(PremiseRequest.class)))
                .thenReturn(response);

        PremiseResponse result =
                premiseController.updatePremise(
                        1,
                        request);

        assertNotNull(result);
        assertEquals(
                "Updated Chennai",
                result.getAddress());

        verify(premiseService, times(1))
                .updatePremise(
                        eq(1),
                        any(PremiseRequest.class));
    }

    @Test
    void testDeletePremise() {

        doNothing().when(premiseService)
                .deletePremise(1);

        String result =
                premiseController.deletePremise(1);

        assertEquals(
                "Premise deleted successfully",
                result);

        verify(premiseService, times(1))
                .deletePremise(1);
    }
}