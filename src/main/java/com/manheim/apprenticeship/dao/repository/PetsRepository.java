package com.manheim.apprenticeship.dao.repository;

import com.manheim.apprenticeship.dao.model.Pets;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass=Pets.class,idClass = int.class)
public interface PetsRepository  {
//select * from pets
    List<Pets> findAll();
    Pets findByName(String name);
    Pets findById(int id);
    void save(Pets pets);
    void delete(Pets pets);

    Pets findByHumanId(int humanId);
}
//@RepositoryDefinition(domainClass = RMSEntity.class, idClass = long.class)
//extends CrudRepository<Customer, Long>
//   https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
//defines association  to database from my model class to dtabase table