package com.healthmanagement.healthmanagement.Controller;

import com.healthmanagement.healthmanagement.ExceptionHandling.ResourceNotFoundException;
import com.healthmanagement.healthmanagement.Entities.Patient;
import com.healthmanagement.healthmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/createPatient")
    public Patient createPatient(@RequestBody Patient patient) {

        //Create patient using name date of birth and gender

        return this.patientRepository.save(patient);

    }

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") int patientId) throws ResourceNotFoundException {

        //Retrieve Patient using id

        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient Not Found For ID : " + patientId));

        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("/updatePatientById/{id}")
    public ResponseEntity<Patient> updatepatient(@PathVariable(value = "id") int patientId,@RequestBody Patient patientDetails) throws ResourceNotFoundException {

        //Update Patient using id

        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient Not Found For ID : " + patientId));

        patient.setName(patientDetails.getName());

        patient.setGender(patientDetails.getGender());

        patient.setDateOfBirth(patientDetails.getDateOfBirth());

        return ResponseEntity.ok(this.patientRepository.save(patient));

    }

    @DeleteMapping("deletePatientById/{id}")
    public Map<String, Boolean> deletPatient(@PathVariable(value = "id") int patientId) throws ResourceNotFoundException {

        //Delete Patient using id

        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient Not Found For ID : " + patientId));

        this.patientRepository.delete(patient);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return response;

    }

    @GetMapping("/getPatients")
    public List<Patient> getAllPatient() {

        //Get all the patients available in the database

        return this.patientRepository.findAll();
    }

    @GetMapping("/searchPatient/{name}")
    public ResponseEntity<List<Patient>> getPatientByName(@PathVariable String name) {

        //Search patient by name

        return new ResponseEntity<>(patientRepository.findByName(name), HttpStatus.OK);

    }


}
