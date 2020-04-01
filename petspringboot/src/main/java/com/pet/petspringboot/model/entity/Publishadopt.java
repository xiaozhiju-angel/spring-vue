package com.pet.petspringboot.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Publishadopt {
    private int publishadoptId;
    private String publishadoptType;
    private Integer publishadoptpetId;
    private int publishadoptuserId;
    private String abandonReason;
    private Date publishTime;
    private String adoptpetType;

    @Id
    @Column(name = "publishadopt_id")
    public int getPublishadoptId() {
        return publishadoptId;
    }

    public void setPublishadoptId(int publishadoptId) {
        this.publishadoptId = publishadoptId;
    }

    @Basic
    @Column(name = "publishadopt_type")
    public String getPublishadoptType() {
        return publishadoptType;
    }

    public void setPublishadoptType(String publishadoptType) {
        this.publishadoptType = publishadoptType;
    }

    @Basic
    @Column(name = "publishadoptpet_id")
    public Integer getPublishadoptpetId() {
        return publishadoptpetId;
    }

    public void setPublishadoptpetId(Integer publishadoptpetId) {
        this.publishadoptpetId = publishadoptpetId;
    }

    @Basic
    @Column(name = "publishadoptuser_id")
    public int getPublishadoptuserId() {
        return publishadoptuserId;
    }

    public void setPublishadoptuserId(int publishadoptuserId) {
        this.publishadoptuserId = publishadoptuserId;
    }

    @Basic
    @Column(name = "abandon_reason")
    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publishadopt that = (Publishadopt) o;
        return publishadoptId == that.publishadoptId &&
                publishadoptuserId == that.publishadoptuserId &&
                Objects.equals(publishadoptType, that.publishadoptType) &&
                Objects.equals(publishadoptpetId, that.publishadoptpetId) &&
                Objects.equals(abandonReason, that.abandonReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishadoptId, publishadoptType, publishadoptpetId, publishadoptuserId, abandonReason);
    }

    @Basic
    @Column(name = "publish_time")
    public Date getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "adoptpet_type")
    public String getAdoptpetType() {
        return adoptpetType;
    }

    public void setAdoptpetType(String adoptpetType) {
        this.adoptpetType = adoptpetType;
    }
}
