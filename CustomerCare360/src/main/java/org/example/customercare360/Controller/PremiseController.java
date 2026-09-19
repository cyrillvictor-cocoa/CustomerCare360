package org.example.customercare360.Controller;

import java.util.List;

import org.example.customercare360.DTO.PremiseRequest;
import org.example.customercare360.DTO.PremiseResponse;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Services.PremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/premises")
public class PremiseController {

    @Autowired
    private PremiseService premiseService;

    // GET ALL Premises
    @GetMapping
    public List<Premise> getAllPremises() {
        return premiseService.getAllPremises();
    }

    // GET Premise By Id
    @GetMapping("/{premiseId}")
    public PremiseResponse getPremiseDetails(
            @PathVariable Integer premiseId) {

        return premiseService.getPremiseDetails(
                premiseId);
    }

    // CREATE Premise
    @PostMapping
    public PremiseResponse createPremise(
            @RequestBody PremiseRequest request) {

        return premiseService.createPremise(
                request);
    }

    // UPDATE Premise
    @PutMapping("/{premiseId}")
    public PremiseResponse updatePremise(
            @PathVariable Integer premiseId,
            @RequestBody PremiseRequest request) {

        return premiseService.updatePremise(
                premiseId,
                request);
    }

    // DELETE Premise
    @DeleteMapping("/{premiseId}")
    public String deletePremise(
            @PathVariable Integer premiseId) {

        premiseService.deletePremise(
                premiseId);

        return "Premise deleted successfully";
    }
}