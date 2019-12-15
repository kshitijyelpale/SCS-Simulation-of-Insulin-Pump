package com.scs.insulinpump.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservoir {

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
    private Patient patient;


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

    public Patient getPatient() {
        return patient;
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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Reservoir{" +
                "reservoirId=" + reservoirId +
                ", level=" + level +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", patient=" + patient +
                '}';
    }
}
