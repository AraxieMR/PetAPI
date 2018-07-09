package com.manheim.apprenticeship.service;

import com.manheim.apprenticeship.dao.model.Human;
import com.manheim.apprenticeship.dao.model.HumanResult;
import com.manheim.apprenticeship.dao.model.Toy;
import com.manheim.apprenticeship.dao.repository.HumansRepository;
import com.manheim.apprenticeship.dao.repository.ToyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //no annotation = no spring bean!
public class HumanService {

    private Logger LOG = LoggerFactory.getLogger(PetsService.class);

@Autowired
private HumansRepository humansRepository;
@Autowired
private ToyRepository toyRepository;

    public Human createAHuman(Human human) {
        LOG.info("Creating a human");
        humansRepository.save(human);
        return human;
    }

    public List<Human> getAllHumans() {
        LOG.info("Finding all humans");
        return humansRepository.findAll();
    }

    public void updateAHuman(Human newHuman, String name) {
       // LOG.info("Finding a pet by name");
        Human human = humansRepository.findByName(name);
        human.setName(newHuman.getName());
        human.setAge(newHuman.getAge());
        humansRepository.save(human);
    }

    public Human findByName(String name){
       return humansRepository.findByName(name);
    }

    public void assignToyToHuman(Human human, Toy toy) {


    }

    public Human findById(int humanId) {
        return humansRepository.findById(humanId);
    }


    public void deleteById(int id) {
        Human hm = findById(id);
       // LOG.info(hm.getName());
        if (hm != null)
            humansRepository.delete(hm);
    }

    public HumanResult getHumanResultById(int id) {
        HumanResult humanResult = new HumanResult();
        Human human = humansRepository.findById(id);
        humanResult.setId(id);
        humanResult.setName(human.getName());
        List<Toy> list = toyRepository.findByHumanId(id);
        humanResult.setToys(list);
        return humanResult;
    }
}
