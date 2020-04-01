package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Staff {
    private int staffId;
    private String staffName;
    private String staffSex;
    private String staffImg;
    private String staffTel;
    private String staffIdcard;
    private String staffLoginame;
    private String staffPass;
    private String staffEmail;
    private String staffProp;
    private Date staffRegtime;
    public Staff(){
        super();
    }
    /* @DateTimeFormat(pattern="yyyy-MM-dd")*/



    public Staff( String staffName, String staffSex, String staffIdcard,
                  String staffTel, String staffEmail,String staffLoginame,
                  String staffPass,String staffProp,String staffImg, Date staffRegtime){
        this.staffName=staffName;
        this.staffSex=staffSex;
        this.staffIdcard=staffIdcard;
        this.staffTel=staffTel;
        this.staffEmail=staffEmail;
        this.staffLoginame=staffLoginame;
        this.staffPass=staffPass;
        this.staffProp=staffProp;
        this.staffImg=staffImg;
        this.staffRegtime=staffRegtime;
    }
    @Id
    @Column(name = "staff_id")
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "staff_sex")
    public String getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }

    @Basic
    @Column(name = "staff_img")
    public String getStaffImg() {
        return staffImg;
    }

    public void setStaffImg(String staffImg) {
        this.staffImg = staffImg;
    }

    @Basic
    @Column(name = "staff_tel")
    public String getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(String staffTel) {
        this.staffTel = staffTel;
    }

    @Basic
    @Column(name = "staff_idcard")
    public String getStaffIdcard() {
        return staffIdcard;
    }

    public void setStaffIdcard(String staffIdcard) {
        this.staffIdcard = staffIdcard;
    }

    @Basic
    @Column(name = "staff__loginame")
    public String getStaffLoginame() {
        return staffLoginame;
    }

    public void setStaffLoginame(String staffLoginame) {
        this.staffLoginame = staffLoginame;
    }

    @Basic
    @Column(name = "staff_pass")
    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }

    @Basic
    @Column(name = "staff_email")
    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    @Basic
    @Column(name = "staff_prop")
    public String getStaffProp() {
        return staffProp;
    }

    public void setStaffProp(String staffProp) {
        this.staffProp = staffProp;
    }

    @Basic
    @Column(name = "staff_regtime")
    public Date getStaffRegtime() {
        return staffRegtime;
    }

    public void setStaffRegtime(Date staffRegtime) {
        this.staffRegtime = staffRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staffId == staff.staffId &&
                Objects.equals(staffName, staff.staffName) &&
                Objects.equals(staffSex, staff.staffSex) &&
                Objects.equals(staffImg, staff.staffImg) &&
                Objects.equals(staffTel, staff.staffTel) &&
                Objects.equals(staffIdcard, staff.staffIdcard) &&
                Objects.equals(staffLoginame, staff.staffLoginame) &&
                Objects.equals(staffPass, staff.staffPass) &&
                Objects.equals(staffEmail, staff.staffEmail) &&
                Objects.equals(staffProp, staff.staffProp) &&
                Objects.equals(staffRegtime, staff.staffRegtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, staffName, staffSex, staffImg, staffTel, staffIdcard, staffLoginame, staffPass, staffEmail, staffProp, staffRegtime);
    }
}
