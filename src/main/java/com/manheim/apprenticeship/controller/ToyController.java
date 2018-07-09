package com.manheim.apprenticeship.controller;

import com.manheim.apprenticeship.dao.model.Human;
import com.manheim.apprenticeship.dao.model.Toy;
import com.manheim.apprenticeship.service.HumanService;
import com.manheim.apprenticeship.service.PetsService;
import com.manheim.apprenticeship.service.ToyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToyController {
    private Logger LOG = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetsService petsService;

    @Autowired
    private HumanService humanService;

    @Autowired
    private ToyService toyService;

    @GetMapping("/toys")
    public List<Toy> getAllToys(){
        LOG.info("In the get all toys method");
        return toyService.getAllToys();
    }

    @PostMapping("/toys")
    public void createAToy(@RequestBody Toy toy){
         toyService.createAToy(toy);
    }
    @PutMapping("/toys")
    public void updateAToy(@RequestBody Toy toy,
                           @RequestParam(name = "name",required = false)String name ){
        LOG.info("Update a toy");
        toyService.updateAtoy(toy,name);
    }
    @PutMapping("/toys/{toyId}/owner")
    private void assignToyToHuman(@PathVariable(name="toyId") int toyId,
                                  @RequestParam(name = "ownerName",required = true)String ownerName ){
        Toy toy = toyService.findById(toyId);
        Human human = humanService.findByName(ownerName);
        toyService.assignToyToHuman(toy,human);
    }
}
