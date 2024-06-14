package com.jo.app.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnexionDto {

	@NotEmpty(message="Email obligatoire")
	@Email
	private String email;
	
	@NotEmpty(message="Le password est obligatoire")
	private String password;
}
