package com.zti.bountyHunter.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date due_date;
    private Integer bounty;
    private String description;
    private Integer status;
    private String hunter_id;
    private String contractor_id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDue_date() {
        return due_date;
    }
    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
    public Integer getBounty() {
        return bounty;
    }
    public void setBounty(Integer bounty) {
        this.bounty = bounty;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getHunterId() {
        return hunter_id;
    }
    public void setHunterId(String hunter_id) {
        this.hunter_id = hunter_id;
    }
    public String getContractorId() {
        return contractor_id;
    }
    public void setContractorId(String contractor_id) {
        this.contractor_id = contractor_id;
    }

    public Contract(Integer id, Date due_date, Integer bounty, String description, Integer status, String hunter_id,
    String contractor_id) {
        this.id = id;
        this.due_date = due_date;
        this.bounty = bounty;
        this.description = description;
        this.status = status;
        this.hunter_id = hunter_id;
        this.contractor_id = contractor_id;
    }
    
    public Contract() {}
}