package com.mackenzie.br.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.PublicationDAO;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@Service
public class PublicationService {
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	public PublicationService() {

	}

	public PublicationDomain publicate(PublicationDomain publicationDomain) throws IllegalArgumentException{
		boolean existsProfessional = professionalDAO.existsByProfessionalID(publicationDomain.getProfessionalID());
		
		if(!existsProfessional) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		PublicationDomain databasePublication = publicationDAO.insert(publicationDomain);	
		
		return databasePublication;
	}

	public void deletePublication(PublicationDomain publicationDomain) {
		boolean existsPublication = publicationDAO.existsByPublicationID(publicationDomain.getPublicationID());
		
		if(!existsPublication) {
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		publicationDAO.deleteById(publicationDomain.getPublicationID());
	}

}
