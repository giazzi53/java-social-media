package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@PostMapping(value = "/publicate")
	public ResponseEntity<PublicationDomain> publicate(@RequestBody PublicationDomain publicationDomain) {
		PublicationDomain databasePublication = publicationService.publicate(publicationDomain);
		
		return new ResponseEntity<>(databasePublication, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deletePublication")
	public ResponseEntity<PublicationDomain> deletePublication(@RequestBody PublicationDomain publicationDomain) {
		PublicationDomain databasePublication = publicationService.deleteByPublicationDate(publicationDomain);
		
		return new ResponseEntity<>(databasePublication, HttpStatus.OK);
	}
	
	@PostMapping(value = "/retrieveListPublication")
	public ResponseEntity<List<PublicationDomain>> retrieveListPublication(@RequestBody ProfessionalDomain professional) throws IllegalAccessException{
		return new ResponseEntity<>(publicationService.retrieveListPublication(professional), HttpStatus.OK);
	}
	
}
