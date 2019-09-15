package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/search")
	public ResponseEntity<List<ProfessionalDomain>> search(@RequestBody ProfessionalDomain professional) {
		List<ProfessionalDomain> listProfessional = searchService.search(professional);
		return new ResponseEntity<>(listProfessional, HttpStatus.OK);
	}
	
}
