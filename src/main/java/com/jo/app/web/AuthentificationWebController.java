package com.jo.app.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jo.app.dto.ConnexionDto;
import com.jo.app.dto.SpectateurDto;
import com.jo.app.entity.User;
import com.jo.app.service.SpectateurService;
import com.jo.app.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthentificationWebController {

	private SpectateurService spectateurService;
	private UserService userService;
	private PasswordEncoder passwordEncoder;

	public AuthentificationWebController(SpectateurService spectateurService, UserService userService, PasswordEncoder passwordEncoder) {
		this.spectateurService = spectateurService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/login")
	public String connexionForm(Model model) {
		ConnexionDto user  = new ConnexionDto();
		model.addAttribute("user", user);
		return "connexion/login";
	}

	@SuppressWarnings("null")
	@PostMapping("/seConnecter")
	public String seConnecter(@Valid @ModelAttribute("user") ConnexionDto user, BindingResult result, Model model) {

		User existingUser = userService.findByEmail(user.getEmail());

		if (existingUser == null) {
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				result.rejectValue("email", null, "Il n'existe pas de compte associé à cet email !");
			}
		} else {
			if (!existingUser.getPassword().equals(user.getPassword())) {
				result.rejectValue("password", null, "Le mot de passe est incorrect !");
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "connexion/login";
		}

		return "organisateur/spectateurs";
	}

	//handler methode to handle spectateur registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {

		SpectateurDto spectateurDto  = new SpectateurDto();
		model.addAttribute("spectateurDto", spectateurDto);

		return "connexion/register";
	}

	@SuppressWarnings("null")
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("spectateurDto") SpectateurDto spectateurDto, BindingResult result, Model model) {

		SpectateurDto existingSpectateur = spectateurService.findByEmail(spectateurDto.getEmail());

		// Validation de l'email
		if(existingSpectateur != null && existingSpectateur.getEmail() != null && !existingSpectateur.getEmail().isEmpty()) {
			result.rejectValue("email", null, "L'email existe déja dans la BD, il doit etre unique !");
		}

		// Validation Password
		if(spectateurDto.getPassword() != null && spectateurDto.getConfirmPassword() != null && !spectateurDto.getPassword().equals(spectateurDto.getConfirmPassword())) {
			result.rejectValue("password", null, "Les deux passwords doivent etre identique !");
			result.rejectValue("confirmPassword", null, "Les deux passwords doivent etre identique !");
		}

		if(result.hasErrors()) {
			model.addAttribute("spectateurDto", spectateurDto);
			return "connexion/register";
		}
		spectateurService.createSpectateur(spectateurDto);
		return "redirect:/register?success";
	}

	@GetMapping("/forgot")
	public String forgotPwdForm(Model model) {
		return "connexion/forgot";
	}

	@PostMapping("/forgot/update")
	public String forgotPwd(Model model, ConnexionDto user) {
		User existingUser = userService.findByEmail(user.getEmail());
		if(existingUser != null) {
			if(user.getPassword().equals(user.getConfirmPassword())) {
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
				userService.save(existingUser);
				model.addAttribute("succes", "Le mots de passe a été modifié avec succés !");
			}else {
				model.addAttribute("error", "Les deux mots de pas ne sont pas dentiques !");
			}
		}else {
			model.addAttribute("error", "L'email fournit n'existe pas dans la base de donnée !");
		}
		return "connexion/forgot";
	}
}
