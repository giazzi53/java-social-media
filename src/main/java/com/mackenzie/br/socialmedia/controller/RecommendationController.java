package com.mackenzie.br.socialmedia.controller;

import java.util.ArrayList;
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

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.RecommendationDomain;
import com.mackenzie.br.socialmedia.service.RecommendationService;

@RestController
public class RecommendationController {
	
	@Autowired
	RecommendationService recommendationService;
	
	@CrossOrigin("*")
	@PostMapping(value="/recommend")
	public ResponseEntity<?> recommend(@RequestBody RecommendationDomain recommendation){
		RecommendationDomain recommendationDomain;
		
		try {
			recommendationDomain = recommendationService.recommend(recommendation);
			
		}catch(IllegalArgumentException i){
			return new ResponseEntity<>(i.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(recommendationDomain, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@PostMapping(value="/getNumberOfRecommendations/{professionalID}")
	public ResponseEntity<?> getNumberOfRecommendations(@PathVariable String professionalID){
		Integer numberOfRecommendations = 0;
		
		try {
			numberOfRecommendations = recommendationService.getNumberOfRecommendations(professionalID);
		}catch(IllegalArgumentException i){
			return new ResponseEntity<>(i.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(numberOfRecommendations, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@GetMapping(value="/getProfessionalsWhoRecommended/{professionalID}")
	public ResponseEntity<?> getProfessionalsWhoRecommended(@PathVariable String professionalID){
		List<ProfessionalDomain> listProfessionalsWhoRecommended = new ArrayList<ProfessionalDomain>();
		
		try {
			listProfessionalsWhoRecommended = recommendationService.getProfessionalsWhoRecommended(professionalID);
		}catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(listProfessionalsWhoRecommended, HttpStatus.OK);
	}
	
}
