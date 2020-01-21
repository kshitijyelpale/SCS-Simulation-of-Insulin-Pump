package com.inglpump.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservoir implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int RESERVOIR_TYPE_INSULIN  = 1;
    public static final int RESERVOIR_TYPE_GLUCAGON = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservoirId")
    private Integer reservoirId;

    private Double level;

    private Integer type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "patientID")
    private User user;


    //================================== getters =======================================================================


    public Integer getReservoirId() {
        return reservoirId;
    }

    public Double getLevel() {
        return level;
    }

    public Integer getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }


    //================================== setters =======================================================================


    public void setReservoirId(Integer reservoirId) {
        this.reservoirId = reservoirId;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservoir{" +
                "reservoirId=" + reservoirId +
                ", level=" + level +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
