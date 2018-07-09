package com.manheim.apprenticeship.dao.repository;

import com.manheim.apprenticeship.dao.model.Human;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Human.class,idClass = int.class)
public interface HumansRepository {
    List<Human> findAll();
    Human findByName(String name);
    void save(Human human);

    Human findById(int humanId);
    void delete(Human human);
}
