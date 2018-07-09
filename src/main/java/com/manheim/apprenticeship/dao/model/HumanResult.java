package com.manheim.apprenticeship.dao.model;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.List;

public class HumanResult {


    private int id;
    private String name;
   // private int age;

    private List<Toy> toys;


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

//    public int getAge() {
//        return age;
//    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    public List<Toy> getToys() {
        return toys;
    }



    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}
