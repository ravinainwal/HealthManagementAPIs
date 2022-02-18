package com.healthmanagement.healthmanagement.repository;

import com.healthmanagement.healthmanagement.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByName(String name);


}
