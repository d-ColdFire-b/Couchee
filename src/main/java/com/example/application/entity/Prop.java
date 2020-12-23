package com.example.application.entity;


public class Prop {

    private Integer id;
    private String name;
    private Integer masterid;
    private Integer typeid;
    private String mastername;
    private String typename;
    private Integer numberof;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public  Prop() {}
    public Prop(String name, Integer masterid, Integer typeid,Integer id) {

        this.id = id;
        this.name = name;
        this.masterid = masterid;
        this.typeid = typeid;
    }

    public Integer getNumberof() {
        return numberof;
    }

    public void setNumberof(Integer numberof) {
        this.numberof = numberof;
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

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
