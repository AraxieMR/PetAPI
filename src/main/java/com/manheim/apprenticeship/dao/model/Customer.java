package com.manheim.apprenticeship.dao.model;

import java.util.List;


public class Customer {

    private String name;
    //private Pets pet;
    private List<Toy> toys;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//   public Pets getPet() {
//        return pet;
//    }
//
//    public void setPet(Pets pet)
//    {
//        this.pet = pet;
//    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}
