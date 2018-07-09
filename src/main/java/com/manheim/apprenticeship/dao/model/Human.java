package com.manheim.apprenticeship.dao.model;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;

@Entity

public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

   // @OneToMany(fetch = FetchType.EAGER, mappedBy = "human", targetEntity = Toy.class)
    //private List<Toy> toys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public List<Toy> getToys() {
//        return toys;
//    }

//    public void setToys(List<Toy> toys) {
//        this.toys = toys;
//    }
}
