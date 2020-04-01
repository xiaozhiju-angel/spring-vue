package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Tipinfo {
    private int tipId;
    private Integer userId;
    private String userName;
    private String userTel;
    private String userEmail;
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
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_tel")
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
        Tipinfo tipinfo = (Tipinfo) o;
        return tipId == tipinfo.tipId &&
                Objects.equals(userId, tipinfo.userId) &&
                Objects.equals(userName, tipinfo.userName) &&
                Objects.equals(userTel, tipinfo.userTel) &&
                Objects.equals(userEmail, tipinfo.userEmail) &&
                Objects.equals(tipDoor, tipinfo.tipDoor) &&
                Objects.equals(place, tipinfo.place) &&
                Objects.equals(detail, tipinfo.detail) &&
                Objects.equals(time, tipinfo.time) &&
                Objects.equals(tipTime, tipinfo.tipTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipId, userId, userName, userTel, userEmail, tipDoor, time, place, detail, tipTime);
    }
}
