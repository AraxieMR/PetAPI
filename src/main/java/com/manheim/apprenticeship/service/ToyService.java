package com.manheim.apprenticeship.service;

import com.manheim.apprenticeship.dao.model.Human;
import com.manheim.apprenticeship.dao.model.Toy;
import com.manheim.apprenticeship.dao.repository.ToyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToyService {
    private Logger LOG = LoggerFactory.getLogger(PetsService.class);

    @Autowired
    private ToyRepository toyRepository;

    public List<Toy> getAllToys() {
        return toyRepository.findAll();
    }

    public void createAToy(Toy toy) {
        toyRepository.save(toy);
    }


    public void updateAtoy(Toy updatedToy, String name) {
        Toy toyFromDb = toyRepository.findByTitle(name);
        toyFromDb.setColor(updatedToy.getColor());
        toyFromDb.setTitle(updatedToy.getTitle());
        toyFromDb.setDescription(updatedToy.getDescription());
        toyRepository.save(toyFromDb);
    }


    public Toy findByTitle(String toyTitle) {
        return toyRepository.findByTitle(toyTitle);
    }

    public List<Toy> findToysByHumanId(int id) {
        return toyRepository.findByHumanId(id);
    }

//    public List<Toy> findToysByHumanName(String name) {
//        return toyRepository.findByName(name);
//    }

    public Toy findById(int toyId) {
        return toyRepository.findById(toyId);
    }

    public void assignToyToHuman(Toy foundToy, Human human) {
        foundToy.setHuman(human);
        toyRepository.save(foundToy);
    }

    public void deleteById(int id) {
          for( Toy toy: findToysByHumanId(id)){
           toyRepository.delete(toy);
       }
    }

//    public void updateById(int id) {
//        findById(id);
//
//
//    }
}
