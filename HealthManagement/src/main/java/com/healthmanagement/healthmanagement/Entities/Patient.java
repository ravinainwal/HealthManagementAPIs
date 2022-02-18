package com.healthmanagement.healthmanagement.Entities;


import javax.persistence.*;
import java.sql.Date;


@Entity  //@Entity To tell the program that this is the entity that we are going to map to the database
@Table(name = "patients") //@Table To tell the program that at which database table we are going to map this entity
public class Patient {

    @Id //@Id tells the program that this field is working as primary key for this database table
    @Column(name = "id")//@Column tells the program that this field is going to be mapped with a database table column name
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@GeneratedValue generation strategy for id cause id can't be null
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    public Patient() {
    }

    public Patient(int id, String name, Date dateOfBirth, String gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
