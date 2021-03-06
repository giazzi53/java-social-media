package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;
import com.mackenzie.br.socialmedia.domain.Professional_InterestTopicDomain;
import com.mackenzie.br.socialmedia.service.Professional_InterestTopicService;

@RestController
public class Professional_InterestTopicController {
	
	@Autowired
	private Professional_InterestTopicService professional_interestTopicService;
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getProfessionalInterestTopics/{professionalID}")	
	public ResponseEntity<?> getProfessionalInterestTopics(@PathVariable String professionalID){
		
		List<InterestTopicDomain> listInterestTopics;
		
		try {
			listInterestTopics = professional_interestTopicService.getProfessionalInterestTopics(professionalID);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listInterestTopics, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/setProfessionalInterestTopics")
	public ResponseEntity<?> setProfessionalInterestTopics(@RequestBody List<Professional_InterestTopicDomain> listProfessionalTopics){
		
		List<Professional_InterestTopicDomain> databaseProfessionalInterestTopics;
		
		try {
			databaseProfessionalInterestTopics = professional_interestTopicService.setProfessionalInterestTopics(listProfessionalTopics);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databaseProfessionalInterestTopics, HttpStatus.OK);
	}
	
}
