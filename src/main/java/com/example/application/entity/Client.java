package com.example.application.entity;


public class Client {

    private  Integer id;
    private String name;
    private Integer discount;

    public Client(){}

    public  Client(String name, Integer discount, Integer id){
        this.id = id;
        this.name = name;
        this.discount = discount;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
