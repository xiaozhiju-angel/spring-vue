package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Fosterpetinfo {
    private int id;
    private String fosterpetName;
    private String fosterpetSex;
    private String fosterpetType;
    private String fosterpetImg;
    private String fosterpetpayornot;
    private String fosterpetallPrice;
    private String fosterpetuserName;
    private String fosterpetuserSex;
    private String fosterpetuserTel;
    private String fosterpetuserEmail;
    private String fosterPricescale;
    private String fosterpetDetail;
    private Integer fosterId;
    private Integer fosterpetId;
    private Integer fosteruserId;
    private Date fosterpetBegintime;
    private Date fosterpetEndtime;
    private Date fostertime;

    public Fosterpetinfo(){
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
    @Column(name = "fosterpet_name")
    public String getFosterpetName() {
        return fosterpetName;
    }

    public void setFosterpetName(String fosterpetName) {
        this.fosterpetName = fosterpetName;
    }

    @Basic
    @Column(name = "fosterpet_sex")
    public String getFosterpetSex() {
        return fosterpetSex;
    }

    public void setFosterpetSex(String fosterpetSex) {
        this.fosterpetSex = fosterpetSex;
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
    @Column(name = "fosterpet_img")
    public String getFosterpetImg() {
        return fosterpetImg;
    }

    public void setFosterpetImg(String fosterpetImg) {
        this.fosterpetImg = fosterpetImg;
    }

    @Basic
    @Column(name = "fosterpetpayornot")
    public String getFosterpetpayornot() {
        return fosterpetpayornot;
    }

    public void setFosterpetpayornot(String fosterpetpayornot) {
        this.fosterpetpayornot = fosterpetpayornot;
    }

    @Basic
    @Column(name = "fosterpetall_price")
    public String getFosterpetallPrice() {
        return fosterpetallPrice;
    }

    public void setFosterpetallPrice(String fosterpetallPrice) {
        this.fosterpetallPrice = fosterpetallPrice;
    }

    @Basic
    @Column(name = "fosterpetuser_name")
    public String getFosterpetuserName() {
        return fosterpetuserName;
    }

    public void setFosterpetuserName(String fosterpetuserName) {
        this.fosterpetuserName = fosterpetuserName;
    }

    @Basic
    @Column(name = "fosterpetuser_sex")
    public String getFosterpetuserSex() {
        return fosterpetuserSex;
    }

    public void setFosterpetuserSex(String fosterpetuserSex) {
        this.fosterpetuserSex = fosterpetuserSex;
    }

    @Basic
    @Column(name = "fosterpetuser_tel")
    public String getFosterpetuserTel() {
        return fosterpetuserTel;
    }

    public void setFosterpetuserTel(String fosterpetuserTel) {
        this.fosterpetuserTel = fosterpetuserTel;
    }

    @Basic
    @Column(name = "fosterpetuser_email")
    public String getFosterpetuserEmail() {
        return fosterpetuserEmail;
    }

    public void setFosterpetuserEmail(String fosterpetuserEmail) {
        this.fosterpetuserEmail = fosterpetuserEmail;
    }

    @Basic
    @Column(name = "foster_pricescale")
    public String getFosterPricescale() {
        return fosterPricescale;
    }

    public void setFosterPricescale(String fosterPricescale) {
        this.fosterPricescale = fosterPricescale;
    }

    @Basic
    @Column(name = "fosterpet_detail")
    public String getFosterpetDetail() {
        return fosterpetDetail;
    }

    public void setFosterpetDetail(String fosterpetDetail) {
        this.fosterpetDetail = fosterpetDetail;
    }

    @Basic
    @Column(name = "foster_id")
    public Integer getFosterId() {
        return fosterId;
    }

    public void setFosterId(Integer fosterId) {
        this.fosterId = fosterId;
    }

    @Basic
    @Column(name = "fosterpet_id")
    public Integer getFosterpetId() {
        return fosterpetId;
    }

    public void setFosterpetId(Integer fosterpetId) {
        this.fosterpetId = fosterpetId;
    }

    @Basic
    @Column(name = "fosteruser_id")
    public Integer getFosteruserId() {
        return fosteruserId;
    }

    public void setFosteruserId(Integer fosteruserId) {
        this.fosteruserId = fosteruserId;
    }

    @Basic
    @Column(name = "fosterpet_begintime")
    public Date getFosterpetBegintime() {
        return fosterpetBegintime;
    }

    public void setFosterpetBegintime(Date fosterpetBegintime) {
        this.fosterpetBegintime = fosterpetBegintime;
    }

    @Basic
    @Column(name = "fosterpet_endtime")
    public Date getFosterpetEndtime() {
        return fosterpetEndtime;
    }

    public void setFosterpetEndtime(Date fosterpetEndtime) {
        this.fosterpetEndtime = fosterpetEndtime;
    }

    @Basic
    @Column(name = "fostertime")
    public Date getFostertime() {
        return fostertime;
    }

    public void setFostertime(Date fostertime) {
        this.fostertime = fostertime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fosterpetinfo that = (Fosterpetinfo) o;
        return id == that.id &&
                Objects.equals(fosterpetName, that.fosterpetName) &&
                Objects.equals(fosterpetSex, that.fosterpetSex) &&
                Objects.equals(fosterpetType, that.fosterpetType) &&
                Objects.equals(fosterpetImg, that.fosterpetImg) &&
                Objects.equals(fosterpetpayornot, that.fosterpetpayornot) &&
                Objects.equals(fosterpetallPrice, that.fosterpetallPrice) &&
                Objects.equals(fosterpetuserName, that.fosterpetuserName) &&
                Objects.equals(fosterpetuserSex, that.fosterpetuserSex) &&
                Objects.equals(fosterpetuserTel, that.fosterpetuserTel) &&
                Objects.equals(fosterpetuserEmail, that.fosterpetuserEmail) &&
                Objects.equals(fosterPricescale, that.fosterPricescale) &&
                Objects.equals(fosterpetDetail, that.fosterpetDetail) &&
                Objects.equals(fosterId, that.fosterId) &&
                Objects.equals(fosterpetId, that.fosterpetId) &&
                Objects.equals(fosteruserId, that.fosteruserId) &&
                Objects.equals(fosterpetBegintime, that.fosterpetBegintime) &&
                Objects.equals(fosterpetEndtime, that.fosterpetEndtime) &&
                Objects.equals(fostertime, that.fostertime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fosterId, fosterpetId, fosteruserId, fosterpetName, fosterpetSex, fosterpetType, fosterpetImg, fosterpetDetail, fosterPricescale, fosterpetallPrice, fosterpetBegintime, fosterpetEndtime, fosterpetpayornot, fosterpetuserName, fosterpetuserSex, fosterpetuserTel, fosterpetuserEmail, fostertime);
    }
}
