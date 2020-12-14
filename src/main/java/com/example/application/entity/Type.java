package com.example.application.entity;


public class Type {

    private Integer id;
    private String name;

    public Type(){}

    public Type(String name, Integer id){

        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
