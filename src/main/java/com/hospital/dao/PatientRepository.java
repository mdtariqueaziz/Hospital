package com.hospital.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.hospital.entities.Patient;


@Service
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	@Query("FROM Patient as p WHERE p.email = :email")
	Patient getPatient(String email);
	
	//public Optional<Patient> findById(Integer id);
}
