package com.healthmanagement.healthmanagement.Entities;



import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;


@Entity //@Entity To tell the program that this is the entity that we are going to map to the database
@Table(name = "appointment") //@Table To tell the program that at which database table we are going to map this entity
public class Appointment {


    @Id //@Id tells the program that this field is working as primary key for this database table
    @Column(name = "id")
//@Column tells the program that this field is going to be mapped with a database table column named as id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//@GeneratedValue generation strategy for id cause id can't be null
    private int id;

    @Column(name = "patientName")
    private String patientName;

    @Column(name = "doctorName")
    private String doctorName;

    @Temporal(TemporalType.DATE)
    @Column(name = "appointmentDate")
    private Date appointmentDate;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;

    @OneToOne(cascade = CascadeType.ALL)
// @OneToOne is used for mapping between the two tables cascade is used for related operations
    private Patient patient;

    public Appointment() {
    }

    public Appointment(int id, String patientName, String doctorName, Date appointmentDate, LocalTime startTime, LocalTime endTime, Patient patient) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
