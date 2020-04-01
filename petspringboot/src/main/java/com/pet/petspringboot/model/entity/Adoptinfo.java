package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Adoptinfo {
    private int id;
    private String petName;
    private String petSex;
    private String petType;
    private String petImg;
    private String petpreuserName;
    private String petpreuserTel;
    private String petpreuserEmail;
    private String petnowuserName;
    private String petnowuserTel;
    private String petnowuserEmail;
    private String abandonReason;
    private Integer petId;
    private Integer preuserId;
    private Integer nowuserId;
    private Date adoptTime;

    public Adoptinfo(){
        super();
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pet_name")
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Basic
    @Column(name = "pet_sex")
    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    @Basic
    @Column(name = "pet_type")
    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    @Basic
    @Column(name = "pet_img")
    public String getPetImg() {
        return petImg;
    }

    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }

    @Basic
    @Column(name = "petpreuser_name")
    public String getPetpreuserName() {
        return petpreuserName;
    }

    public void setPetpreuserName(String petpreuserName) {
        this.petpreuserName = petpreuserName;
    }

    @Basic
    @Column(name = "petpreuser_tel")
    public String getPetpreuserTel() {
        return petpreuserTel;
    }

    public void setPetpreuserTel(String petpreuserTel) {
        this.petpreuserTel = petpreuserTel;
    }

    @Basic
    @Column(name = "petpreuser_email")
    public String getPetpreuserEmail() {
        return petpreuserEmail;
    }

    public void setPetpreuserEmail(String petpreuserEmail) {
        this.petpreuserEmail = petpreuserEmail;
    }

    @Basic
    @Column(name = "petnowuser_name")
    public String getPetnowuserName() {
        return petnowuserName;
    }

    public void setPetnowuserName(String petnowuserName) {
        this.petnowuserName = petnowuserName;
    }

    @Basic
    @Column(name = "petnowuser_tel")
    public String getPetnowuserTel() {
        return petnowuserTel;
    }

    public void setPetnowuserTel(String petnowuserTel) {
        this.petnowuserTel = petnowuserTel;
    }

    @Basic
    @Column(name = "petnowuser_email")
    public String getPetnowuserEmail() {
        return petnowuserEmail;
    }

    public void setPetnowuserEmail(String petnowuserEmail) {
        this.petnowuserEmail = petnowuserEmail;
    }

    @Basic
    @Column(name = "abandon_reason")
    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }

    @Basic
    @Column(name = "pet_id")
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "preuser_id")
    public Integer getPreuserId() {
        return preuserId;
    }

    public void setPreuserId(Integer preuserId) {
        this.preuserId = preuserId;
    }

    @Basic
    @Column(name = "nowuser_id")
    public Integer getNowuserId() {
        return nowuserId;
    }

    public void setNowuserId(Integer nowuserId) {
        this.nowuserId = nowuserId;
    }

    @Basic
    @Column(name = "adopt_time")
    public Date getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(Date adoptTime) {
        this.adoptTime = adoptTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoptinfo adoptinfo = (Adoptinfo) o;
        return id == adoptinfo.id &&
                Objects.equals(petName, adoptinfo.petName) &&
                Objects.equals(petSex, adoptinfo.petSex) &&
                Objects.equals(petType, adoptinfo.petType) &&
                Objects.equals(petImg, adoptinfo.petImg) &&
                Objects.equals(petpreuserName, adoptinfo.petpreuserName) &&
                Objects.equals(petpreuserTel, adoptinfo.petpreuserTel) &&
                Objects.equals(petpreuserEmail, adoptinfo.petpreuserEmail) &&
                Objects.equals(petnowuserName, adoptinfo.petnowuserName) &&
                Objects.equals(petnowuserTel, adoptinfo.petnowuserTel) &&
                Objects.equals(petnowuserEmail, adoptinfo.petnowuserEmail) &&
                Objects.equals(abandonReason, adoptinfo.abandonReason) &&
                Objects.equals(petId, adoptinfo.petId) &&
                Objects.equals(preuserId, adoptinfo.preuserId) &&
                Objects.equals(nowuserId, adoptinfo.nowuserId) &&
                Objects.equals(adoptTime, adoptinfo.adoptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, preuserId, nowuserId, petName, petSex, petType, petImg, petpreuserName, petpreuserTel, petpreuserEmail, petnowuserName, petnowuserTel, petnowuserEmail, abandonReason, adoptTime);
    }
}
