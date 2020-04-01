package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pet {
    private int petId;
    private String petName;
    private String petSex;
    private int petuserId;
    private Date petrabidPrevaccin;
    private Date petrabidNextvaccin;
    private String petImg;
    private String petType;
    private Date petrecodTime;
    private String petisuserApply;
    public Pet(){
        super();
    }

    public Pet( String petName, String petSex,String petType,int petuserId,String petImg,
                Date petrabidPrevaccin, Date petrabidNextvaccin,String petisuserApply,
                Date petrecodTime){
        this.petName=petName;
        this.petSex=petSex;
        this.petType=petType;
        this.petuserId=petuserId;
        this.petImg=petImg;
        this.petrabidPrevaccin=petrabidPrevaccin;
        this.petrabidNextvaccin=petrabidNextvaccin;
        this.petisuserApply=petisuserApply;
        this.petrecodTime=petrecodTime;
    }

    @Id
    @Column(name = "pet_id")
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
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
    @Column(name = "petuser_id")
    public int getPetuserId() {
        return petuserId;
    }

    public void setPetuserId(int petuserId) {
        this.petuserId = petuserId;
    }

    @Basic
    @Column(name = "petrabid_prevaccin")
    public Date getPetrabidPrevaccin() {
        return petrabidPrevaccin;
    }

    public void setPetrabidPrevaccin(Date petrabidPrevaccin) {
        this.petrabidPrevaccin = petrabidPrevaccin;
    }

    @Basic
    @Column(name = "petrabid_nextvaccin")
    public Date getPetrabidNextvaccin() {
        return petrabidNextvaccin;
    }

    public void setPetrabidNextvaccin(Date petrabidNextvaccin) {
        this.petrabidNextvaccin = petrabidNextvaccin;
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
    @Column(name = "pet_type")
    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    @Basic
    @Column(name = "petrecod_time")
    public Date getPetrecodTime() {
        return petrecodTime;
    }

    public void setPetrecodTime(Date petrecodTime) {
        this.petrecodTime = petrecodTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return petId == pet.petId &&
                petuserId == pet.petuserId &&
                Objects.equals(petName, pet.petName) &&
                Objects.equals(petSex, pet.petSex) &&
                Objects.equals(petrabidPrevaccin, pet.petrabidPrevaccin) &&
                Objects.equals(petrabidNextvaccin, pet.petrabidNextvaccin) &&
                Objects.equals(petImg, pet.petImg) &&
                Objects.equals(petType, pet.petType) &&
                Objects.equals(petrecodTime, pet.petrecodTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, petName, petSex, petuserId, petrabidPrevaccin, petrabidNextvaccin, petImg, petType, petrecodTime);
    }

    @Basic
    @Column(name = "petisuser_apply")
    public String getPetisuserApply() {
        return petisuserApply;
    }

    public void setPetisuserApply(String petisuserApply) {
        this.petisuserApply = petisuserApply;
    }
}
