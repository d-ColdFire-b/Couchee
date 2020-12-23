package com.example.application.entity;


import java.util.Date;


public class Waybill {

    private Integer id;
    private Integer clientid;
    private Date date;
    private String clientname;
    private Integer sum;
    private String status;

    public String getStatus() {
        return status;
    }

    public Waybill (){}

    public Waybill(Integer clientid, Date date, Integer id) {
        this.clientid = clientid;
        this.date = date;
        this.id = id;

    }

    public String getStatus(String string) {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
