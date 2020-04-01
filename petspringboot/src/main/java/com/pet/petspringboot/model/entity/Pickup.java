package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pickup {
    private int pickupId;
    private int pickupuserId;
    private String pickuppetSex;
    private String pickuppetImg;
    private String pickuppetPlace;
    private String pickupState;
    private String pickupDetail;
    private String pickuppetType;
    private Date pickuppetTime;
    private Date pickpubTime;

    @Id
    @Column(name = "pickup_id")
    public int getPickupId() {
        return pickupId;
    }

    public void setPickupId(int pickupId) {
        this.pickupId = pickupId;
    }

    @Basic
    @Column(name = "pickupuser_id")
    public int getPickupuserId() {
        return pickupuserId;
    }

    public void setPickupuserId(int pickupuserId) {
        this.pickupuserId = pickupuserId;
    }

    @Basic
    @Column(name = "pickuppet_sex")
    public String getPickuppetSex() {
        return pickuppetSex;
    }

    public void setPickuppetSex(String pickuppetSex) {
        this.pickuppetSex = pickuppetSex;
    }

    @Basic
    @Column(name = "pickuppet_img")
    public String getPickuppetImg() {
        return pickuppetImg;
    }

    public void setPickuppetImg(String pickuppetImg) {
        this.pickuppetImg = pickuppetImg;
    }

    @Basic
    @Column(name = "pickuppet_place")
    public String getPickuppetPlace() {
        return pickuppetPlace;
    }

    public void setPickuppetPlace(String pickuppetPlace) {
        this.pickuppetPlace = pickuppetPlace;
    }

    @Basic
    @Column(name = "pickup_state")
    public String getPickupState() {
        return pickupState;
    }

    public void setPickupState(String pickupState) {
        this.pickupState = pickupState;
    }

    @Basic
    @Column(name = "pickup_detail")
    public String getPickupDetail() {
        return pickupDetail;
    }

    public void setPickupDetail(String pickupDetail) {
        this.pickupDetail = pickupDetail;
    }

    @Basic
    @Column(name = "pickuppet_type")
    public String getPickuppetType() {
        return pickuppetType;
    }

    public void setPickuppetType(String pickuppetType) {
        this.pickuppetType = pickuppetType;
    }

    @Basic
    @Column(name = "pickuppet_time")
    public Date getPickuppetTime() {
        return pickuppetTime;
    }

    public void setPickuppetTime(Date pickuppetTime) {
        this.pickuppetTime = pickuppetTime;
    }

    @Basic
    @Column(name = "pickpub_time")
    public Date getPickpubTime() {
        return pickpubTime;
    }

    public void setPickpubTime(Date pickpubTime) {
        this.pickpubTime = pickpubTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pickup pickup = (Pickup) o;
        return pickupId == pickup.pickupId &&
                pickupuserId == pickup.pickupuserId &&
                Objects.equals(pickuppetSex, pickup.pickuppetSex) &&
                Objects.equals(pickuppetImg, pickup.pickuppetImg) &&
                Objects.equals(pickuppetPlace, pickup.pickuppetPlace) &&
                Objects.equals(pickupState, pickup.pickupState) &&
                Objects.equals(pickupDetail, pickup.pickupDetail) &&
                Objects.equals(pickuppetType, pickup.pickuppetType) &&
                Objects.equals(pickuppetTime, pickup.pickuppetTime) &&
                Objects.equals(pickpubTime, pickup.pickpubTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupId, pickupuserId, pickuppetSex, pickuppetImg, pickuppetType, pickuppetTime, pickuppetPlace, pickupState, pickupDetail, pickpubTime);
    }
}
