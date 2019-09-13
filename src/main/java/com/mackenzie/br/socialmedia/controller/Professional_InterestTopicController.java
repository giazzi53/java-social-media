package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.Professional_InterestTopicDomain;
import com.mackenzie.br.socialmedia.service.Professional_InterestTopicService;

@RestController
public class Professional_InterestTopicController {
	
	@Autowired
	private Professional_InterestTopicService professional_interestTopicService;
	
	@GetMapping(value = "/getProfessionalInterestTopics")
	public ResponseEntity<List<Professional_InterestTopicDomain>> getProfessionalInterestTopics(@RequestBody String professionalID){
		List<Professional_InterestTopicDomain> databaseProfessionalInterestTopics;
		
		try {
			databaseProfessionalInterestTopics = professional_interestTopicService.getProfessionalInterestTopics(professionalID);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databaseProfessionalInterestTopics, HttpStatus.OK);
	}
	
}
