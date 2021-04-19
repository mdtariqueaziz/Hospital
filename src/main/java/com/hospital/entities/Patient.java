package com.hospital.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name is required!")
	@Length(min = 3, message = "Name should be at least 3 character!")
	private String name;
	//@NotBlank(message = "Email is required!")
//	@Column(unique = true)
//	@Email(regexp = ".+@.+\\..+")
	private String email;
	@NotBlank(message = "Address is required!")
	@Length(min = 4, message = "Address should be at least 10 character!")
	private String address;
	@NotBlank(message = "Phone is required!")
	@Length(min = 10, message = "Phone should be at least 10 digit!")
	private String phone;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")
	private String password;
	private String role;
	private boolean enabled;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Patient(int id,
			@NotBlank(message = "Name is required!") @Length(min = 3, message = "Name should be at least 3 character!") String name,
			@NotBlank(message = "Email is required!") @Email(regexp = ".+@.+\\..+") String email,
			@NotBlank(message = "Address is required!") @Length(min = 10, message = "Address should be at least 10 character!") String address,
			@NotBlank(message = "Phone is required!") @Length(min = 10, message = "Phone should be at least 10 digit!") @NotBlank(message = "Phone is required!") @Length(min = 10, message = "Phone should be at least 10 digit!") String phone,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$") String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.password = password;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone=" + phone
				+ ", password=" + password + "]";
	}

}
