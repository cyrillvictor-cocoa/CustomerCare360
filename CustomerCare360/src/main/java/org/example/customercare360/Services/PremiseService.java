package org.example.customercare360.Services;

import java.util.List;

import org.example.customercare360.DTO.PremiseRequest;
import org.example.customercare360.DTO.PremiseResponse;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.PremiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiseService {

    @Autowired
    private PremiseRepository premiseRepository;

    // GET ALL Premises
    public List<Premise> getAllPremises() {
        return premiseRepository.findAll();
    }

    // GET Premise By Id
    public PremiseResponse getPremiseDetails(Integer premiseId) {

        Premise premise = premiseRepository.findById(premiseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Premise not found with id: " + premiseId));

        return mapToResponse(premise);
    }

    // CREATE Premise
    public PremiseResponse createPremise(PremiseRequest request) {

        Premise premise = new Premise();

        premise.setAddress(request.getAddress());
        premise.setRegion(request.getRegion());
        premise.setMeterId(request.getMeterId());
        premise.setStatus(request.getStatus());
        premise.setCustomerId(request.getCustomerId());
        premise.setCreatedBy(request.getCreatedBy());
        premise.setModifiedBy(request.getModifiedBy());

        Premise savedPremise = premiseRepository.save(premise);

        return mapToResponse(savedPremise);
    }

    // UPDATE Premise
    public PremiseResponse updatePremise(
            Integer premiseId,
            PremiseRequest request) {

        Premise existingPremise =
                premiseRepository.findById(premiseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Premise not found with id: "
                                                + premiseId));

        existingPremise.setAddress(request.getAddress());
        existingPremise.setRegion(request.getRegion());
        existingPremise.setMeterId(request.getMeterId());
        existingPremise.setStatus(request.getStatus());
        existingPremise.setCustomerId(request.getCustomerId());
        existingPremise.setCreatedBy(request.getCreatedBy());
        existingPremise.setModifiedBy(request.getModifiedBy());

        Premise updatedPremise =
                premiseRepository.save(existingPremise);

        return mapToResponse(updatedPremise);
    }

    // DELETE Premise
    public void deletePremise(Integer premiseId) {

        Premise existingPremise =
                premiseRepository.findById(premiseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Premise not found with id: "
                                                + premiseId));

        premiseRepository.delete(existingPremise);
    }

    // DTO Mapping
    private PremiseResponse mapToResponse(
            Premise premise) {

        PremiseResponse response =
                new PremiseResponse();

        response.setPremiseId(
                premise.getPremiseId());

        response.setAddress(
                premise.getAddress());

        response.setRegion(
                premise.getRegion());

        response.setMeterId(
                premise.getMeterId());

        response.setStatus(
                premise.getStatus());

        response.setCustomerId(
                premise.getCustomerId());

        response.setCreatedBy(
                premise.getCreatedBy());

        response.setModifiedBy(
                premise.getModifiedBy());

        return response;
    }
}