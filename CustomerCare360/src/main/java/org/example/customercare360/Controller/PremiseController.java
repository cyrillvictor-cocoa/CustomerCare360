package org.example.customercare360.Controller;

import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Services.PremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/premises")
public class PremiseController {

    @Autowired
    private PremiseService premiseService;

    // VIEW Premise
    @GetMapping("/{premiseId}")
    public Premise getPremiseDetails(
            @PathVariable Integer premiseId) {

        return premiseService.getPremiseDetails(
                premiseId);
    }

    // CREATE Premise
    @PostMapping
    public Premise createPremise(
            @RequestBody Premise premise) {

        return premiseService.createPremise(
                premise);
    }

    // UPDATE Premise
    @PutMapping("/{premiseId}")
    public Premise updatePremise(
            @PathVariable Integer premiseId,
            @RequestBody Premise premise) {

        return premiseService.updatePremise(
                premiseId,
                premise);
    }
}