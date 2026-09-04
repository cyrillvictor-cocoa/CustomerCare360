package org.example.customercare360.Services;

import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.PremiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiseService {

    @Autowired
    private PremiseRepository premiseRepository;

    // GET Premise
    public Premise getPremiseDetails(Integer premiseId) {

        return premiseRepository.findById(premiseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Premise not found with id: " + premiseId));
    }

    // CREATE Premise
    public Premise createPremise(Premise premise) {
        return premiseRepository.save(premise);
    }

    // UPDATE Premise
    public Premise updatePremise(
            Integer premiseId,
            Premise updatedPremise) {

        Premise existingPremise =
                premiseRepository.findById(premiseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Premise not found with id: "
                                                + premiseId));

        existingPremise.setAddress(updatedPremise.getAddress());
        existingPremise.setRegion(updatedPremise.getRegion());
        existingPremise.setMeterId(updatedPremise.getMeterId());
        existingPremise.setStatus(updatedPremise.getStatus());
        existingPremise.setCustomerId(updatedPremise.getCustomerId());
        existingPremise.setCreatedBy(updatedPremise.getCreatedBy());
        existingPremise.setModifiedBy(updatedPremise.getModifiedBy());

        return premiseRepository.save(existingPremise);
    }
}