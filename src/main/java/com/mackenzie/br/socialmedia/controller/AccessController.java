package com.mackenzie.br.socialmedia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
//import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
//import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;
import com.mackenzie.br.socialmedia.service.AccessService;

@RestController
public class AccessController {

	@Autowired
	AccessService accessService;

	@CrossOrigin(value = "*")
	@PostMapping(value = "/signUp")
	public ResponseEntity<?> signUp(@RequestBody @Valid ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try {
			databaseProfessional = accessService.signUp(professional);
			
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.CREATED);
	}

	@CrossOrigin(value = "*")
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try {
			databaseProfessional = accessService.login(professional);
		} catch (IllegalAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.OK);
	}

	@CrossOrigin(value = "*")
	@PutMapping(value = "/updateProfile")
	public ResponseEntity<?> updateProfile(@RequestBody ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try{
			databaseProfessional = accessService.updateProfile(professional);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.OK);
	}
	
	@CrossOrigin(value = "*")
	@GetMapping(value = "/retrieveProfessionalData/{professionalID}")
	public ResponseEntity<?> retrieveProfessionalData(@PathVariable String professionalID) {
		ProfessionalDomain databaseProfessional;
		
		try {
			databaseProfessional = accessService.retrieveProfessionalData(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.OK);
	}
	
}
