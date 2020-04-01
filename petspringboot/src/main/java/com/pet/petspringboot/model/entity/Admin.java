package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Admin {
    private int adminId;
    private String adminName;
    private String adminSex;
    private String adminImg;
    private String adminIdcard;
    private String adminTel;
    private String adminEmail;
    private String adminPass;
    private String adminProp;
    private String adminLoginame;
    private Date adminRegtime;

    public Admin(){
        super();
    }
    /* @DateTimeFormat(pattern="yyyy-MM-dd")*/

    public Admin( String adminName, String adminSex, String adminIdcard,
                  String adminTel, String adminEmail,String adminLoginame,
                  String adminPass,String adminProp, String adminImg,Date adminRegtime){
        this.adminName=adminName;
        this.adminSex=adminSex;
        this.adminIdcard=adminIdcard;
        this.adminTel=adminTel;
        this.adminEmail=adminEmail;
        this.adminLoginame=adminLoginame;
        this.adminPass=adminPass;
        this.adminProp=adminProp;
        this.adminImg=adminImg;
        this.adminRegtime=adminRegtime;
    }
    @Id
    @Column(name = "admin_id")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "admin_name")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "admin_sex")
    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    @Basic
    @Column(name = "admin_img")
    public String getAdminImg() {
        return adminImg;
    }

    public void setAdminImg(String adminImg) {
        this.adminImg = adminImg;
    }

    @Basic
    @Column(name = "admin_idcard")
    public String getAdminIdcard() {
        return adminIdcard;
    }

    public void setAdminIdcard(String adminIdcard) {
        this.adminIdcard = adminIdcard;
    }

    @Basic
    @Column(name = "admin_tel")
    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    @Basic
    @Column(name = "admin_email")
    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Basic
    @Column(name = "admin_pass")
    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    @Basic
    @Column(name = "admin_prop")
    public String getAdminProp() {
        return adminProp;
    }

    public void setAdminProp(String adminProp) {
        this.adminProp = adminProp;
    }

    @Basic
    @Column(name = "admin_loginame")
    public String getAdminLoginame() {
        return adminLoginame;
    }

    public void setAdminLoginame(String adminLoginame) {
        this.adminLoginame = adminLoginame;
    }

    @Basic
    @Column(name = "admin_regtime")
    public Date getAdminRegtime() {
        return adminRegtime;
    }

    public void setAdminRegtime(Date adminRegtime) {
        this.adminRegtime = adminRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return adminId == admin.adminId &&
                Objects.equals(adminName, admin.adminName) &&
                Objects.equals(adminSex, admin.adminSex) &&
                Objects.equals(adminImg, admin.adminImg) &&
                Objects.equals(adminIdcard, admin.adminIdcard) &&
                Objects.equals(adminTel, admin.adminTel) &&
                Objects.equals(adminEmail, admin.adminEmail) &&
                Objects.equals(adminPass, admin.adminPass) &&
                Objects.equals(adminProp, admin.adminProp) &&
                Objects.equals(adminLoginame, admin.adminLoginame) &&
                Objects.equals(adminRegtime, admin.adminRegtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminName, adminSex, adminImg, adminIdcard, adminTel, adminEmail, adminPass, adminProp, adminLoginame, adminRegtime);
    }
}
