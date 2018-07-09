package com.manheim.apprenticeship.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;

@Entity
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    @JsonIgnore
    private String description;
    @Column(name = "color")
    private String color;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE},targetEntity=Human.class)
    @JoinColumn(name = "human_id" )
    //private int human_id;

    @JsonIgnore
    private Human human;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //public int getHumanId() {
    ////    return human_id;
    //}

    //public void setHumanId(int id) {
    //    this.human_id = id;
    //}



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

//    @Override
//    public String toString(){
//        return getTitle();
//    }
}
