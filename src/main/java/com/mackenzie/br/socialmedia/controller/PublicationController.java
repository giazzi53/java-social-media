package com.mackenzie.br.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.PublicationDAO;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@RestController
public class PublicationController {
	
	@Autowired
	private PublicationDAO publicationDAO;

	@RequestMapping(value = "/publicate", method = RequestMethod.POST)
	public String publicate(@RequestBody PublicationDomain publicationDomain) {
		
		publicationDAO.insert(publicationDomain);
		
		return "Publicação realizada com sucesso";
	}
	
	@RequestMapping(value = "/deletePublicaton", method = RequestMethod.DELETE)
	public String deletePublicaton(@RequestBody PublicationDomain publicationDomain) {
		
		publicationDAO.deleteByPulicationDate(publicationDomain);
		
		return "Publicação deletada com sucesso";
	}
}
