package com.example.application.form;

public class Propform {

    private String name;
    private Integer masterid;
    private Integer typeid;
    private Integer numberof;
    private Integer id;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberof() {
        return numberof;
    }

    public void setNumberof(Integer numberof) {
        this.numberof = numberof;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMasterid() {
        return masterid;
    }

    public void setMasterid(Integer masterid) {
        this.masterid = masterid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}
