package com.mackenzie.br.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.PublicationDomain;
import com.mackenzie.br.socialmedia.service.PublicationService;

@RestController
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;

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
	
	@DeleteMapping(value = "/deletePublication")
	public ResponseEntity<PublicationDomain> deletePublication(@RequestBody PublicationDomain publicationDomain) {				
		try{
			publicationService.deletePublication(publicationDomain);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
