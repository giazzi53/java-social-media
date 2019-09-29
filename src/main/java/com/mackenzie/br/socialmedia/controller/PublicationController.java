package com.mackenzie.br.socialmedia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;
import com.mackenzie.br.socialmedia.domain.PublicationReactionDomain;
import com.mackenzie.br.socialmedia.service.PublicationService;

@RestController
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/publicate")
	public ResponseEntity<?> publicate(@RequestBody @Valid PublicationDomain publicationDomain) {
		PublicationDomain databasePublication;
				
		try{
			databasePublication = publicationService.publicate(publicationDomain);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databasePublication, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "/deletePublication/{publicationID}")
	public ResponseEntity<?> deletePublication(@PathVariable String publicationID) {				
		try{
			publicationService.deletePublication(publicationID);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/retrievePublicationList/{professionalID}")
	public ResponseEntity<?> retrievePublicationList(@PathVariable String professionalID) {
		List<PublicationDomain> publicationList = null;
		
		try {
			publicationList = publicationService.retrievePublicationList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/retrieveFeedPublicationsList/{professionalID}")
	public ResponseEntity<?> retrieveFeedPublicationsList(@PathVariable String professionalID) {
		List<PublicationDomain> publicationList = null;
		
		try {
			publicationList = publicationService.retrieveFeedPublicationsList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "reactToPublication")
	public ResponseEntity<?> reactToPublication(@RequestBody PublicationReactionDomain publicationReactionDomain) {
		PublicationReactionDomain publicationReaction = new PublicationReactionDomain();
		
		try {
			publicationReaction = publicationService.reactToPublication(publicationReactionDomain);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(publicationReaction, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "unreactToPublication")
	public ResponseEntity<?> unreactToPublication(@RequestBody PublicationReactionDomain publicationReactionDomain) {		
		try {
			publicationService.unreactToPublication(publicationReactionDomain);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "getNumberReactionsOfPublication/{publicationID}")
	public ResponseEntity<?> getNumberReactionsOfPublication(@PathVariable String publicationID) {
		int numberOfReactions = 0;
		
		try {
			numberOfReactions = publicationService.getNumberReactionsOfPublication(publicationID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(numberOfReactions ,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "getProfessionalsWhoRecommendedPublication/{publicationID}")
	public ResponseEntity<?> getProfessionalsWhoRecommendedPublication(@PathVariable String publicationID) {
		List<ProfessionalDomain> listProfessionalsWhoRecommendedPublication =
				new ArrayList<ProfessionalDomain>();
		
		try {
			listProfessionalsWhoRecommendedPublication = publicationService.getProfessionalsWhoRecommendedPublication(publicationID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(listProfessionalsWhoRecommendedPublication ,HttpStatus.OK);
	}
	
}
