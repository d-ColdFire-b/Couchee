package com.example.application.entity;

import java.util.Date;

public class Report {



    private String propname;
    private String propid;
    private Integer waybillid;
    private Date waybillfate;

    public Report(){}

    public Report(String propname, String propid, Integer waybillid, Date waybillfate) {
        this.propname = propname;
        this.propid = propid;
        this.waybillid = waybillid;
        this.waybillfate = waybillfate;
    }

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname;
    }

    public String getPropid() {
        return propid;
    }

    public void setPropid(String propid) {
        this.propid = propid;
    }

    public Integer getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(Integer waybillid) {
        this.waybillid = waybillid;
    }

    public Date getWaybillfate() {
        return waybillfate;
    }

    public void setWaybillfate(Date waybillfate) {
        this.waybillfate = waybillfate;
    }
}
