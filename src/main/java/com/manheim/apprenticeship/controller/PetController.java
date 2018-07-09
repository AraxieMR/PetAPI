package com.manheim.apprenticeship.controller;

import com.manheim.apprenticeship.dao.model.Human;
import com.manheim.apprenticeship.dao.model.Pets;
import com.manheim.apprenticeship.service.HumanService;
import com.manheim.apprenticeship.service.PetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    private Logger LOG = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetsService petsService;

    @Autowired
    private HumanService humanService;

    @GetMapping("/pets")
    public List<Pets> getAllPets() {
        LOG.info("inside Getall pets method");
        return petsService.getAllPets();
    }

    @PostMapping("/pets")
    private void createAPet(@RequestBody Pets pet){
        LOG.info("Create a pet");
        petsService.createAPet(pet);
    }

    @PutMapping("/pets")
    private void updateAPet(@RequestBody Pets pet,
                            @RequestParam(name = "name",required=false)String name){
        LOG.info("Update a pet");
        petsService.updateAPet(pet,name);
    }
    @PutMapping("/pets/{petId}/owner")
    private void assignHumanToPet(@PathVariable(name="petId") int petId,
                                  @RequestParam(name = "ownerName",required = true)String ownerName ){
        //  RequestParam  =  ?
        Human human = humanService.findByName(ownerName); //the found human will be placed in variable human
        Pets pet  = petsService.findById(petId);
        petsService.assignHumanToPet(pet,human);
    }

}
