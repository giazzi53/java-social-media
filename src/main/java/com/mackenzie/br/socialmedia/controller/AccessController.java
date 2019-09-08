package com.mackenzie.br.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@PostMapping(value = "/signUp")
	public ResponseEntity<ProfessionalDomain> signUp(@RequestBody ProfessionalDomain professional) {
//		ProfessionalDomain databaseProfessional;
//		try {
//			databaseProfessional = accessService.login(professional);
//		} catch (IllegalAccessException e) {
//			return new ResponseEntity<>(professional, HttpStatus.UNAUTHORIZED);
//		}
		ProfessionalDomain databaseProfessional = accessService.signUp(professional);
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@GetMapping(value = "/login")
	public ResponseEntity<ProfessionalDomain> login(@RequestBody ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try {
			databaseProfessional = accessService.login(professional);
		} catch (IllegalAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@PutMapping(value = "/updateProfile")
	public ResponseEntity<ProfessionalDomain> updateProfile(@RequestBody ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try{
			databaseProfessional = accessService.updateProfile(professional);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@PostMapping(value = "/retrieveProfessionalData")
	public ResponseEntity<ProfessionalDomain> retrieveProfessionalData(@RequestBody ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional;
		
		try {
			databaseProfessional = accessService.retrieveProfessionalData(professional);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(databaseProfessional, HttpStatus.CREATED);
	}
	
}
