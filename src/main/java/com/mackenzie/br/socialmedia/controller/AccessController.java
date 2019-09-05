package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
//import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
//import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@RestController
public class AccessController {

	@Autowired
	private ProfessionalDAO professionalDAO;

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<ProfessionalDomain> signUp(@RequestBody ProfessionalDomain professional) {
		professionalDAO.insert(professional);
		return new ResponseEntity<>(professional, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestBody ProfessionalDomain professional) {
		
		List<ProfessionalDomain> userLoginProfessional = professionalDAO.findByUserLogin(professional.getUserLogin());
		List<ProfessionalDomain> passwordProfessional = professionalDAO.findByPassword(professional.getPassword());
	
		if((!(userLoginProfessional.size() == 1)) || passwordProfessional.isEmpty()) { 
			// login query list must return only one object
			// password query list must return one or more objects (same password can be used for different users)
			return "Usuário ou senha incorretos";
		}
		
		return "Bem vindo, " + userLoginProfessional.get(0).getName();
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<ProfessionalDomain> updateProfilePost(@RequestBody ProfessionalDomain professional) {
		
		List<ProfessionalDomain> profissional = professionalDAO.findByUserLogin(professional.getUserLogin());
		profissional.get(0).setName(professional.getName());
		professionalDAO.save(profissional.get(0));

		return new ResponseEntity<>(professional,HttpStatus.OK);
	}
}
