package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Lose {
    private int loseId;
    private int losepetId;
    private int loseuserId;
    private String losepetPlace;
    private String losepetDetail;
    private Date losepetTime;
    private Date losepubTime;

    @Id
    @Column(name = "lose_id")
    public int getLoseId() {
        return loseId;
    }

    public void setLoseId(int loseId) {
        this.loseId = loseId;
    }

    @Basic
    @Column(name = "losepet_id")
    public int getLosepetId() {
        return losepetId;
    }

    public void setLosepetId(int losepetId) {
        this.losepetId = losepetId;
    }

    @Basic
    @Column(name = "loseuser_id")
    public int getLoseuserId() {
        return loseuserId;
    }

    public void setLoseuserId(int loseuserId) {
        this.loseuserId = loseuserId;
    }

    @Basic
    @Column(name = "losepet_place")
    public String getLosepetPlace() {
        return losepetPlace;
    }

    public void setLosepetPlace(String losepetPlace) {
        this.losepetPlace = losepetPlace;
    }

    @Basic
    @Column(name = "losepet_detail")
    public String getLosepetDetail() {
        return losepetDetail;
    }

    public void setLosepetDetail(String losepetDetail) {
        this.losepetDetail = losepetDetail;
    }

    @Basic
    @Column(name = "losepet_time")
    public Date getLosepetTime() {
        return losepetTime;
    }

    public void setLosepetTime(Date losepetTime) {
        this.losepetTime = losepetTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lose lose = (Lose) o;
        return loseId == lose.loseId &&
                losepetId == lose.losepetId &&
                loseuserId == lose.loseuserId &&
                Objects.equals(losepetPlace, lose.losepetPlace) &&
                Objects.equals(losepetDetail, lose.losepetDetail) &&
                Objects.equals(losepetTime, lose.losepetTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loseId, losepetId, loseuserId, losepetTime, losepetPlace, losepetDetail);
    }

    @Basic
    @Column(name = "losepub_time")
    public Date getLosepubTime() {
        return losepubTime;
    }

    public void setLosepubTime(Date losepubTime) {
        this.losepubTime = losepubTime;
    }
}
