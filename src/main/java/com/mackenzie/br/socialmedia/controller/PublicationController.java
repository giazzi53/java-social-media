package com.mackenzie.br.socialmedia.controller;

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
import com.mackenzie.br.socialmedia.service.PublicationService;

@RestController
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;

	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@PostMapping(value = "/publicate")
	public ResponseEntity<PublicationDomain> publicate(@RequestBody @Valid PublicationDomain publicationDomain) {
		PublicationDomain databasePublication;
				
		try{
			databasePublication = publicationService.publicate(publicationDomain);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databasePublication, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@DeleteMapping(value = "/deletePublication/{publicationID}")
	public ResponseEntity<PublicationDomain> deletePublication(@PathVariable String publicationID) {				
		try{
			publicationService.deletePublication(publicationID);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@GetMapping(value = "/retrievePublicationList/{professionalID}")
	public ResponseEntity<List<PublicationDomain>> retrievePublicationList(@PathVariable String professionalID) {
		List<PublicationDomain> publicationList = null;
//		ProfessionalDomain professionalDomain = new ProfessionalDomain();
//		professionalDomain.setProfessionalID(professionalID);
		
		try {
			publicationList = publicationService.retrievePublicationList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@GetMapping(value = "/retrieveFeedPublicationsList/{professionalID}")
	public ResponseEntity<List<PublicationDomain>> retrieveFeedPublicationsList(@PathVariable String professionalID) {
		List<PublicationDomain> publicationList = null;
//		ProfessionalDomain professionalDomain = new ProfessionalDomain();
//		professionalDomain.setProfessionalID(professionalID);
		
		try {
			publicationList = publicationService.retrieveFeedPublicationsList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}
	
	
}
