package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.PublicationDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@Service
public class PublicationService {
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	public PublicationService() {
		super();
	}

	public PublicationDomain publicate(PublicationDomain publicationDomain) {
		PublicationDomain databasePublication = publicationDAO.insert(publicationDomain);	
		
		return databasePublication;
	}

	public PublicationDomain deleteByPublicationDate(PublicationDomain publicationDomain) {
		List<PublicationDomain> databasePublication = publicationDAO.deleteByPublicationDate(publicationDomain);
		
		return databasePublication.get(0);
	}

}
