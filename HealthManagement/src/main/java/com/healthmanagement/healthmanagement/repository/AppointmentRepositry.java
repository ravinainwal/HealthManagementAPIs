package com.healthmanagement.healthmanagement.repository;

import com.healthmanagement.healthmanagement.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepositry extends JpaRepository<Appointment, Integer> {

    @Query("select a from Appointment  a where a.patientName like %?1%" + "or a.doctorName like %?1%")
    List<Appointment> findByName(String name);


    List<Appointment> findAllByAppointmentDate(Date appointmentDate);

}
