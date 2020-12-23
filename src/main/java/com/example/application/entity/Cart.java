package com.example.application.entity;


public class Cart {

    private Integer id;
    private Integer waybillid;
    private Integer propid;
    private Integer rownumb;
    private Integer price;
    private String propname;
    private Integer numberof;

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

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(Integer waybillid) {
        this.waybillid = waybillid;
    }

    public Integer getPropid() {
        return propid;
    }

    public void setPropid(Integer propid) {
        this.propid = propid;
    }

    public Integer getRownumb() {
        return rownumb;
    }

    public void setRownumb(Integer rownumb) {
        this.rownumb = rownumb;
    }
}
