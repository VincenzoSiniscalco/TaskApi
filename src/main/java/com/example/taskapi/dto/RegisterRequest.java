package com.example.taskapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

	@NotBlank(message = "Email obbligatoria")
	@Email(message = "Email non valida")
	private String email;

	@NotBlank(message = "Password obbligatoria")
	@Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
	private String password;


	public RegisterRequest() {}

	public RegisterRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
