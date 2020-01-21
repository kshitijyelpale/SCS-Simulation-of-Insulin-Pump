package com.inglpump.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "userlog")
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private User user;


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

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "PatientLog{" +
            "logId=" + logId +
            ", message='" + message + '\'' +
            ", timestamp=" + timestamp +
            ", user=" + user +
            '}';
    }
}

