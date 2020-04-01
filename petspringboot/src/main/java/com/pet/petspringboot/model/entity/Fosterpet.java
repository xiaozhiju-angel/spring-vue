package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Fosterpet {
    private int fosterId;
    private int fosterpetId;
    private int fosteruserId;
    private String fosterpetType;
    private String fosterPrice;
    private Date fosterpetbeginTime;
    private Date fosterpetendTime;
    private Date fostertime;
    private String fosterpetisuserApply;

    @Id
    @Column(name = "foster_id")
    public int getFosterId() {
        return fosterId;
    }

    public void setFosterId(int fosterId) {
        this.fosterId = fosterId;
    }

    @Basic
    @Column(name = "fosterpet_id")
    public int getFosterpetId() {
        return fosterpetId;
    }

    public void setFosterpetId(int fosterpetId) {
        this.fosterpetId = fosterpetId;
    }

    @Basic
    @Column(name = "fosteruser_id")
    public int getFosteruserId() {
        return fosteruserId;
    }

    public void setFosteruserId(int fosteruserId) {
        this.fosteruserId = fosteruserId;
    }

    @Basic
    @Column(name = "fosterpet_type")
    public String getFosterpetType() {
        return fosterpetType;
    }

    public void setFosterpetType(String fosterpetType) {
        this.fosterpetType = fosterpetType;
    }

    @Basic
    @Column(name = "fosterpetbegin_time")
    public Date getFosterpetbeginTime() {
        return fosterpetbeginTime;
    }


    public void setFosterpetbeginTime(Date fosterpetbeginTime) {
        this.fosterpetbeginTime = fosterpetbeginTime;
    }

    @Basic
    @Column(name = "fosterpetend_time")
    public Date getFosterpetendTime() {
        return fosterpetendTime;
    }

    public void setFosterpetendTime(Date fosterpetendTime) {
        this.fosterpetendTime = fosterpetendTime;
    }

    @Basic
    @Column(name = "fostertime")
    public Date getFostertime() {
        return fostertime;
    }


    public void setFostertime(Date fostertime) {
        this.fostertime = fostertime;
    }

    @Basic
    @Column(name = "foster_price")
    public String getFosterPrice() {
        return fosterPrice;
    }

    public void setFosterPrice(String fosterPrice) {
        this.fosterPrice = fosterPrice;
    }

    @Basic
    @Column(name = "fosterpetisuser_apply")
    public String getFosterpetisuserApply() {
        return fosterpetisuserApply;
    }

    public void setFosterpetisuserApply(String fosterpetisuserApply) {
        this.fosterpetisuserApply = fosterpetisuserApply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fosterpet fosterpet = (Fosterpet) o;
        return fosterId == fosterpet.fosterId &&
                fosterpetId == fosterpet.fosterpetId &&
                fosteruserId == fosterpet.fosteruserId &&
                Objects.equals(fosterpetType, fosterpet.fosterpetType) &&
                Objects.equals(fosterPrice, fosterpet.fosterPrice) &&
                Objects.equals(fosterpetbeginTime, fosterpet.fosterpetbeginTime) &&
                Objects.equals(fosterpetendTime, fosterpet.fosterpetendTime) &&
                Objects.equals(fostertime, fosterpet.fostertime) &&
                Objects.equals(fosterpetisuserApply, fosterpet.fosterpetisuserApply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fosterId, fosterpetId, fosteruserId, fosterpetType, fosterpetbeginTime, fosterpetendTime, fosterPrice, fosterpetisuserApply, fostertime);
    }
}
