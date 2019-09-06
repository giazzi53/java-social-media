package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;
import com.mackenzie.br.socialmedia.service.PublicationService;

@RestController
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;

	// @CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@PostMapping(value = "/publicate")
	public ResponseEntity<PublicationDomain> publicate(@RequestBody PublicationDomain publicationDomain) {
		PublicationDomain databasePublication;
				
		try{
			databasePublication = publicationService.publicate(publicationDomain);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(databasePublication, HttpStatus.CREATED);
	}
	
	// @CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@DeleteMapping(value = "/deletePublication")
	public ResponseEntity<PublicationDomain> deletePublication(@RequestBody PublicationDomain publicationDomain) {				
		try{
			publicationService.deletePublication(publicationDomain);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	// @CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@GetMapping(value = "/retrievePublicationList")
	public ResponseEntity<List<PublicationDomain>> retrievePublicationList(@RequestBody ProfessionalDomain professionalDomain) {
		List<PublicationDomain> publicationList = null;
		
		try {
			publicationList = publicationService.retrievePublicationList(professionalDomain);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publicationList, HttpStatus.OK);
	}
	
}
