package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Tip {
    private int tipId;
    private int userId;
    private String tipDoor;
    private String place;
    private String detail;
    private Date time;
    private Date tipTime;

    @Id
    @Column(name = "tip_id")
    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    @Basic
    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tip_door")
    public String getTipDoor() {
        return tipDoor;
    }

    public void setTipDoor(String tipDoor) {
        this.tipDoor = tipDoor;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "tip_time")
    public Date getTipTime() {
        return tipTime;
    }

    public void setTipTime(Date tipTime) {
        this.tipTime = tipTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tip tip = (Tip) o;
        return tipId == tip.tipId &&
                userId == tip.userId &&
                Objects.equals(tipDoor, tip.tipDoor) &&
                Objects.equals(place, tip.place) &&
                Objects.equals(detail, tip.detail) &&
                Objects.equals(time, tip.time) &&
                Objects.equals(tipTime, tip.tipTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipId, userId, tipDoor, time, place, detail, tipTime);
    }
}
