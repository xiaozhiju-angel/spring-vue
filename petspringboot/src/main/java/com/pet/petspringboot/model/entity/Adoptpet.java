package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Adoptpet {
    private int adoptId;
    private int adoptpetId;
    private int adoptpreuserId;
    private int adoptnextuserId;
    private String abandonReason;
    private Date adoptTime;


    public Adoptpet(){
        super();
    }
    @Id
    @Column(name = "adopt_id")
    public int getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(int adoptId) {
        this.adoptId = adoptId;
    }

    @Basic
    @Column(name = "adoptpet_id")
    public int getAdoptpetId() {
        return adoptpetId;
    }

    public void setAdoptpetId(int adoptpetId) {
        this.adoptpetId = adoptpetId;
    }

    @Basic
    @Column(name = "adoptpreuser_id")
    public int getAdoptpreuserId() {
        return adoptpreuserId;
    }

    public void setAdoptpreuserId(int adoptpreuserId) {
        this.adoptpreuserId = adoptpreuserId;
    }

    @Basic
    @Column(name = "adoptnextuser_id")
    public int getAdoptnextuserId() {
        return adoptnextuserId;
    }

    public void setAdoptnextuserId(int adoptnextuserId) {
        this.adoptnextuserId = adoptnextuserId;
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
        Adoptpet adoptpet = (Adoptpet) o;
        return adoptId == adoptpet.adoptId &&
                adoptpetId == adoptpet.adoptpetId &&
                adoptpreuserId == adoptpet.adoptpreuserId &&
                adoptnextuserId == adoptpet.adoptnextuserId &&
                Objects.equals(abandonReason, adoptpet.abandonReason) &&
                Objects.equals(adoptTime, adoptpet.adoptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adoptId, adoptpetId, adoptpreuserId, adoptnextuserId, abandonReason, adoptTime);
    }
}
