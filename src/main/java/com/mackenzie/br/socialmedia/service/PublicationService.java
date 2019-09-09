package com.mackenzie.br.socialmedia.service;

import java.util.Date;
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

	}

	public PublicationDomain publicate(PublicationDomain publicationDomain) throws IllegalArgumentException{
		boolean existsProfessional = professionalDAO.existsByProfessionalID(publicationDomain.getProfessionalID());
		
		if(!existsProfessional) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		publicationDomain.setPublicationDate(new Date());
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
	
	public List<PublicationDomain> retrievePublicationList(ProfessionalDomain professionalDomain) throws IllegalArgumentException{
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professionalDomain.getProfessionalID());

		if(!existsProfessional){
			throw new IllegalArgumentException("Usário não encontrado");
		}
		
		List<PublicationDomain> publicationList = publicationDAO.findAllByProfessionalID(professionalDomain.getProfessionalID());
		
		return publicationList;
	}
	
//	public boolean validateProfessionalID(String id) {
//	
//		List<ProfessionalDomain> listProfessionals = professionalDAO.findAll();
//		
//		for (ProfessionalDomain professional : listProfessionals) {
//			if (professional.getProfessionalID().equalsIgnoreCase(id)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public boolean validateUser(String userLogin) {
//		for (ProfessionalDomain professional : professionalDAO.findAll()){
//			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
//				return true;
//			}
//		}
//		return false;
//	}

}
