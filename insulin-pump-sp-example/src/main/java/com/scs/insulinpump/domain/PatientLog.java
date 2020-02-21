package com.scs.insulinpump.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patientlog")
public class PatientLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "logID")
    private Integer logId;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "patientID")
    private Patient patient;


    //================================== getters =======================================================================


    public Integer getLogId() {
        return logId;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Patient getPatient() {
        return patient;
    }


    //================================== setters =======================================================================


    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    @Override
    public String toString() {
        return "PatientLog{" +
                "logId=" + logId +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", patient=" + patient +
                '}';
    }
}
