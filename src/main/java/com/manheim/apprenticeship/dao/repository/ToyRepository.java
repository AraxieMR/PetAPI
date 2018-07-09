package com.manheim.apprenticeship.dao.repository;

import com.manheim.apprenticeship.dao.model.Toy;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Toy.class,idClass = int.class)
public interface ToyRepository {
    List<Toy> findAll();
    List<Toy> findByHumanId(int id);
    Toy findByTitle(String title);  //(here just the value you looking for)
    Toy findById(int id);
    void save(Toy toy);

    //List<Toy> findByName(String name);

    void delete(Toy toy);

}
