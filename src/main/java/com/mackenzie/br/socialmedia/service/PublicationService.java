package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.PublicationDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@Service
public class PublicationService {
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
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
	
	public List<PublicationDomain> retrieveListPublication(ProfessionalDomain professional) throws IllegalAccessException{
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professional.getUserLogin());
		List<PublicationDomain> listPublications = publicationDAO.findByProfessionalID(professional0.get(0).getProfessionalID());
		
		return listPublications;
		
	}
	
	public boolean validateProfessionalID(String id) {
	
		List<ProfessionalDomain> listProfessionals = professionalDAO.findAll();
		
		for (ProfessionalDomain professional : listProfessionals) {
			if (professional.getProfessionalID().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validateUser(String userLogin) {
		for (ProfessionalDomain professional : professionalDAO.findAll()){
			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
				return true;
			}
		}
		return false;
	}

}
