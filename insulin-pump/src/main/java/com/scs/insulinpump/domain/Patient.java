package com.scs.insulinpump.domain;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patientID")
    private Integer patientId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    @Column(name = "contacts", length = 512, columnDefinition = "TEXT")
    private String contactList;

    @Past
    private Date birthdate;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<PatientLog> logs;


    //================================== getters =======================================================================


    public Integer getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getContactList() {
        return contactList;
    }

    public Date getBirthdate() {
        return birthdate;
    }


    //================================== setters =======================================================================


    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactList(String contactList) {
        this.contactList = contactList;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactList='" + contactList + '\'' +
                ", birthdate=" + birthdate +
                ", logs=" + logs +
                '}';
    }
}
