package com.healthmanagement.healthmanagement.Controller;

import com.healthmanagement.healthmanagement.Entities.Appointment;
import com.healthmanagement.healthmanagement.ExceptionHandling.ResourceNotFoundException;
import com.healthmanagement.healthmanagement.repository.AppointmentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    @Autowired
    AppointmentRepositry appointmentRepositry;


    @PostMapping("/createAppointment")

    //Create appointment

    public Appointment createAppointment(@RequestBody Appointment appointment) {

        return this.appointmentRepositry.save(appointment);
    }


    //Get appointment using Id
    @GetMapping("/getAppointmentById/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable(value = "id") int appointmentId) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepositry.findById(appointmentId).orElseThrow(() ->
                new ResourceNotFoundException("Appointment Not Found For ID : " + appointmentId));

        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping("/updateAppointmentById/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") int appointmentId,  @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {

        //Update appointment using id

        Appointment appointment = appointmentRepositry.findById(appointmentId).orElseThrow(() ->
                new ResourceNotFoundException("Appointment Not Found For ID : " + appointmentId));

        appointment.setId(appointmentDetails.getId());

        appointment.setDoctorName(appointmentDetails.getDoctorName());

        appointment.setPatientName(appointmentDetails.getPatientName());

        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());

        appointment.setStartTime(appointmentDetails.getStartTime());

        appointment.setEndTime(appointmentDetails.getEndTime());

        return ResponseEntity.ok(this.appointmentRepositry.save(appointment));

    }

    @DeleteMapping("deleteAppointmentById/{id}")
    public Map<String, Boolean> deleteAppointment(@PathVariable(value = "id") int appointmentId) throws ResourceNotFoundException {

        //Delete appointment using id

        Appointment appointment = appointmentRepositry.findById(appointmentId).orElseThrow(() ->
                new ResourceNotFoundException("Appointment Not Found For ID : " + appointmentId));

        this.appointmentRepositry.delete(appointment);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return response;

    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAllAppointments() {

        //Get all the appointments available in the database

        return this.appointmentRepositry.findAll();
    }

    @GetMapping("/searchAppointmentByName/{name}")
    public ResponseEntity<List<Appointment>> getAppointmentsByNameAndDoctorName(@PathVariable String name) {

        //Search Appointment by name and doctor's name

        return new ResponseEntity<>(appointmentRepositry.findByName(name), HttpStatus.OK);

    }

    @GetMapping("/searchAppointmentByDate/{appointmentDate}")
    public ResponseEntity<List<Appointment>> getAppointmentByDate(@PathVariable Date appointmentDate) {

        //Search all appointments of a particular date

        return new ResponseEntity<>(appointmentRepositry.findAllByAppointmentDate(appointmentDate), HttpStatus.OK);

    }


}
