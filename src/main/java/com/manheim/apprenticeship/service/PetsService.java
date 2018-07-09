package com.manheim.apprenticeship.service;


import com.manheim.apprenticeship.dao.model.Human;
import com.manheim.apprenticeship.dao.model.Pets;
import com.manheim.apprenticeship.dao.repository.PetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {
    private Logger LOG = LoggerFactory.getLogger(PetsService.class);


    @Autowired
    private PetsRepository petsRepository;

    public List<Pets> getAllPets() {
        LOG.info("Finding all the pets");
        return petsRepository.findAll();
    }

    public void createAPet(Pets pet) {
        LOG.info("Creating a pet");
        petsRepository.save(pet);
    }

    public void updateAPet(Pets dragon, String name) {
        LOG.info("Finding a pet by name");
        Pets wolfyInDB = petsRepository.findByName(name);


        wolfyInDB.setName(dragon.getName());
        wolfyInDB.setBreed(dragon.getBreed());
        wolfyInDB.setColor(dragon.getColor());
        petsRepository.save(wolfyInDB);
    }

    public Pets findById(int petId) {
        return petsRepository.findById(petId);
    }

    public Pets findByHumanId(int humanId) {
        LOG.info(humanId + "");
        return petsRepository.findByHumanId(humanId);
    }

    public void assignHumanToPet(Pets pet, Human foundHuman) {
        pet.setHuman(foundHuman);
        petsRepository.save(pet);
    }

    public void deleteById(int id) {
        Pets pet = findByHumanId(id);
        if (pet != null)
            petsRepository.delete(pet);//need
    }
}

