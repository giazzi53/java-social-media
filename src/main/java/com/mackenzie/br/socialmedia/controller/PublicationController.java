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
	@GetMapping(value = "/retrievePublicationsList/{professionalID}")
	public ResponseEntity<?> retrievePublicationsList(@PathVariable String professionalID) {
		
		List<PublicationDomain> publicationsList;
		
		try {
			publicationsList = publicationService.retrievePublicationsList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationsList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/retrieveFeedPublicationsList/{professionalID}")
	public ResponseEntity<?> retrieveFeedPublicationsList(@PathVariable String professionalID) {
		
		List<PublicationDomain> publicationsList;
		
		try {
			publicationsList = publicationService.retrieveFeedPublicationsList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationsList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/reactToPublication")
	public ResponseEntity<?> reactToPublication(@RequestBody PublicationReactionDomain publicationReactionDomain) {
		
		PublicationReactionDomain publicationReaction = new PublicationReactionDomain();
		
		try {
			publicationReaction = publicationService.reactToPublication(publicationReactionDomain);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationReaction, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/unreactToPublication")
	public ResponseEntity<?> unreactToPublication(@RequestBody PublicationReactionDomain publicationReactionDomain) {		
		
		try {
			publicationService.unreactToPublication(publicationReactionDomain);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getNumberOfPublicationReactions/{publicationID}")
	public ResponseEntity<?> getNumberOfPublicationReactions(@PathVariable String publicationID) {
		
		int numberOfReactions = 0;
		
		try {
			numberOfReactions = publicationService.getNumberOfPublicationReactions(publicationID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(numberOfReactions, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getProfessionalsWhoReactedToPublication/{publicationID}")
	public ResponseEntity<?> getProfessionalsWhoReactedToPublication(@PathVariable String publicationID) {
		
		List<String> ProfessionalsWhoReactedToPublicationList =
				new ArrayList<String>();
		
		try {
			ProfessionalsWhoReactedToPublicationList = publicationService.getProfessionalsWhoReactedToPublication(publicationID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(ProfessionalsWhoReactedToPublicationList ,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getPublicationStatus/{publicationID}")
	public ResponseEntity<?> getPublicationStatus(@PathVariable String publicationID) {
		
		int status;
		
		try {
			status = publicationService.getPublicationStatus(publicationID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(status ,HttpStatus.OK);
	}
	
}
