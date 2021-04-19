package com.hospital.controler;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.dao.PatientRepository;
import com.hospital.entities.Patient;

@Controller

public class Patients {

	@Autowired
	private PatientRepository patientRepository;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@PostMapping("/do_register")
	public String registre(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult,
			boolean agreement, Model model, HttpSession session) {
		try {
			if (bindingResult.hasErrors()) {
				System.out.println(bindingResult);
				model.addAttribute("patient", patient);
				return "signup";
			}

			Patient result = this.patientRepository.save(patient);
			System.out.println(result);
			model.addAttribute("patient", new Patient());

			System.out.println(patient);
			return "signup";

		} catch (Exception e) {
			e.getMessage();
			model.addAttribute("patient", patient);
			return "signup";
		}

	}

	@PostMapping("/dologin")
	public String doLogin(@Param("email") String email, @Param("password") String password, Model m) {
		Patient patient = patientRepository.getPatient(email);
		if (patient != null && patient.getPassword().equals(password)) {
			m.addAttribute("patient", patient);
			return "dashboard";
		}
		return "loginfail";
	}

	@GetMapping("/all_patients")
	public String getPatients(Model m) {
		List<Patient> patients = patientRepository.findAll();
		m.addAttribute("patients", patients);
		return "view_patients";
	}

	@GetMapping("/delete/{id}")
	public String deletePatient(@PathVariable("id") Integer id) {
		Optional<Patient> findById = patientRepository.findById(id);
		Patient patient = findById.get();

		System.out.println(patient);
		patientRepository.delete(patient);
		return "view_patients";
	}
	  @GetMapping("/update-patient/{id}")
      public String update(Model m,@PathVariable("id")Integer id) {
		  Optional<Patient> findById = patientRepository.findById(id);
		  Patient patient = findById.get();
		  m.addAttribute("patient", patient);
		  
    	  return "update";
      }



	// update contact handler
	@PostMapping(
			path = "/do-update",
			consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String updateHandler(Patient patient) {
        Patient exist_patient = patientRepository.findById(patient.getId()).get();
		exist_patient.setName(patient.getName());
		exist_patient.setPhone(patient.getPhone());
		exist_patient.setAddress(patient.getAddress());
		patientRepository.save(exist_patient);
		return "dashboard";
	}

}
