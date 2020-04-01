package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    private String userName;
    private String userSex;
    private String userIdcard;
    private String userTel;
    private String userEmail;
    private String userDoor;
    private String userisTenant;
    private String userLoginame;
    private String userPass;
    private String userProp;
    private String userImg;
    private Date userRegtime;

    public User(){
        super();
    }
    /* @DateTimeFormat(pattern="yyyy-MM-dd")*/


    public User( String userName, String userSex, String userIdcard,
                 String userTel, String userEmail, String userDoor, String userisTenant,
                 String userLoginame,String userPass,String userProp,String userImg, Date userRegtime){
        this.userName=userName;
        this.userSex=userSex;
        this.userIdcard=userIdcard;
        this.userTel=userTel;
        this.userEmail=userEmail;
        this.userDoor=userDoor;
        this.userisTenant=userisTenant;
        this.userLoginame=userLoginame;
        this.userPass=userPass;
        this.userProp=userProp;
        this.userImg=userImg;
        this.userRegtime=userRegtime;
    }
    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_idcard")
    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
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
    @Column(name = "user_door")
    public String getUserDoor() {
        return userDoor;
    }

    public void setUserDoor(String userDoor) {
        this.userDoor = userDoor;
    }

    @Basic
    @Column(name = "useris_tenant")
    public String getUserisTenant() {
        return userisTenant;
    }

    public void setUserisTenant(String userisTenant) {
        this.userisTenant = userisTenant;
    }

    @Basic
    @Column(name = "user_loginame")
    public String getUserLoginame() {
        return userLoginame;
    }

    public void setUserLoginame(String userLoginame) {
        this.userLoginame = userLoginame;
    }

    @Basic
    @Column(name = "user_pass")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "user_prop")
    public String getUserProp() {
        return userProp;
    }

    public void setUserProp(String userProp) {
        this.userProp = userProp;
    }

    @Basic
    @Column(name = "user_img")
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Basic
    @Column(name = "user_regtime")
    public Date getUserRegtime() {
        return userRegtime;
    }

    public void setUserRegtime(Date userRegtime) {
        this.userRegtime = userRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userSex, user.userSex) &&
                Objects.equals(userIdcard, user.userIdcard) &&
                Objects.equals(userTel, user.userTel) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userDoor, user.userDoor) &&
                Objects.equals(userisTenant, user.userisTenant) &&
                Objects.equals(userLoginame, user.userLoginame) &&
                Objects.equals(userPass, user.userPass) &&
                Objects.equals(userProp, user.userProp) &&
                Objects.equals(userImg, user.userImg) &&
                Objects.equals(userRegtime, user.userRegtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userSex, userIdcard, userTel, userEmail, userDoor, userisTenant, userLoginame, userPass, userProp, userImg, userRegtime);
    }
}
