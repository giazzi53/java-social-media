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
import com.mackenzie.br.socialmedia.service.AccessService;

@RestController
public class AccessController {

	@Autowired
	AccessService accessService;

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<ProfessionalDomain> signUp(@RequestBody ProfessionalDomain professional) {
		return accessService.signUp(professional);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestBody ProfessionalDomain professional) {
		return accessService.login(professional);
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<ProfessionalDomain> updateProfile(@RequestBody ProfessionalDomain professional) {
		return accessService.updateProfile(professional);
	}
}
