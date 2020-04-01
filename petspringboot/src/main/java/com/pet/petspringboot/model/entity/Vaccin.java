package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Vaccin {
    private int vaccinId;
    private String petName;
    private String petSex;
    private String petType;
    private String userName;
    private String userSex;
    private String userTel;
    private String userEmail;
    private Integer petId;
    private Integer userId;
    private Date petpreVaccin;
    private Date petnextVaccin;
    private Date petRegtime;

    @Id
    @Column(name = "vaccin_id")
    public int getVaccinId() {
        return vaccinId;
    }

    public void setVaccinId(int vaccinId) {
        this.vaccinId = vaccinId;
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
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
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
    @Column(name = "pet_id")
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
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
    @Column(name = "petpre_vaccin")
    public Date getPetpreVaccin() {
        return petpreVaccin;
    }

    public void setPetpreVaccin(Date petpreVaccin) {
        this.petpreVaccin = petpreVaccin;
    }

    @Basic
    @Column(name = "petnext_vaccin")
    public Date getPetnextVaccin() {
        return petnextVaccin;
    }

    public void setPetnextVaccin(Date petnextVaccin) {
        this.petnextVaccin = petnextVaccin;
    }

    @Basic
    @Column(name = "pet_regtime")
    public Date getPetRegtime() {
        return petRegtime;
    }

    public void setPetRegtime(Date petRegtime) {
        this.petRegtime = petRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccin vaccin = (Vaccin) o;
        return vaccinId == vaccin.vaccinId &&
                Objects.equals(petName, vaccin.petName) &&
                Objects.equals(petSex, vaccin.petSex) &&
                Objects.equals(petType, vaccin.petType) &&
                Objects.equals(userName, vaccin.userName) &&
                Objects.equals(userSex, vaccin.userSex) &&
                Objects.equals(userTel, vaccin.userTel) &&
                Objects.equals(userEmail, vaccin.userEmail) &&
                Objects.equals(petId, vaccin.petId) &&
                Objects.equals(userId, vaccin.userId) &&
                Objects.equals(petpreVaccin, vaccin.petpreVaccin) &&
                Objects.equals(petnextVaccin, vaccin.petnextVaccin) &&
                Objects.equals(petRegtime, vaccin.petRegtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccinId, petId, userId, petName, petSex, petType, userName, userSex, userTel, userEmail, petpreVaccin, petnextVaccin, petRegtime);
    }
}
