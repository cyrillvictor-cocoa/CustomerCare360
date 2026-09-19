package org.example.customercare360.Services;

import org.example.customercare360.DTO.PremiseRequest;
import org.example.customercare360.DTO.PremiseResponse;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Repository.PremiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PremiseServiceTest {

    @Mock
    private PremiseRepository premiseRepository;

    @InjectMocks
    private PremiseService premiseService;

    @Test
    void testGetAllPremises() {

        List<Premise> premises = new ArrayList<>();

        Premise premise = new Premise();
        premise.setPremiseId(1);

        premises.add(premise);

        when(premiseRepository.findAll())
                .thenReturn(premises);

        List<Premise> result =
                premiseService.getAllPremises();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(premiseRepository,
                times(1))
                .findAll();
    }

    @Test
    void testGetPremiseDetails() {

        Premise premise = new Premise();

        premise.setPremiseId(1);
        premise.setAddress("Anna Nagar");

        when(premiseRepository.findById(1))
                .thenReturn(Optional.of(premise));

        PremiseResponse result =
                premiseService.getPremiseDetails(1);

        assertNotNull(result);
        assertEquals(
                "Anna Nagar",
                result.getAddress());

        verify(premiseRepository,
                times(1))
                .findById(1);
    }

    @Test
    void testCreatePremise() {

        PremiseRequest request =
                new PremiseRequest();

        request.setAddress("Chennai");
        request.setRegion("North");
        request.setMeterId(1001);

        Premise savedPremise =
                new Premise();

        savedPremise.setPremiseId(1);
        savedPremise.setAddress("Chennai");

        when(premiseRepository.save(any(Premise.class)))
                .thenReturn(savedPremise);

        PremiseResponse result =
                premiseService.createPremise(request);

        assertNotNull(result);
        assertEquals(
                "Chennai",
                result.getAddress());

        verify(premiseRepository,
                times(1))
                .save(any(Premise.class));
    }

    @Test
    void testUpdatePremise() {

        Premise existingPremise =
                new Premise();

        existingPremise.setPremiseId(1);
        existingPremise.setAddress("Old Address");

        PremiseRequest request =
                new PremiseRequest();

        request.setAddress("New Address");

        Premise updatedPremise =
                new Premise();

        updatedPremise.setPremiseId(1);
        updatedPremise.setAddress("New Address");

        when(premiseRepository.findById(1))
                .thenReturn(Optional.of(existingPremise));

        when(premiseRepository.save(any(Premise.class)))
                .thenReturn(updatedPremise);

        PremiseResponse result =
                premiseService.updatePremise(
                        1,
                        request);

        assertNotNull(result);
        assertEquals(
                "New Address",
                result.getAddress());

        verify(premiseRepository,
                times(1))
                .findById(1);

        verify(premiseRepository,
                times(1))
                .save(any(Premise.class));
    }

    @Test
    void testDeletePremise() {

        Premise premise =
                new Premise();

        premise.setPremiseId(1);

        when(premiseRepository.findById(1))
                .thenReturn(Optional.of(premise));

        premiseService.deletePremise(1);

        verify(premiseRepository,
                times(1))
                .findById(1);

        verify(premiseRepository,
                times(1))
                .delete(premise);
    }
}