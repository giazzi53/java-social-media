package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.PublicationDAO;
import com.mackenzie.br.socialmedia.DAO.PublicationReactionDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;
import com.mackenzie.br.socialmedia.domain.PublicationReactionDomain;
import com.mackenzie.br.socialmedia.map.PublicationMapper;

@Service
public class PublicationService {
	
	@Autowired
	private PublicationDAO publicationDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private PublicationMapper publicationMapper;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private PublicationReactionDAO publicationRectionDAO;
	
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

	public void deletePublication(String publicationID) {
		boolean existsPublication = publicationDAO.existsByPublicationID(publicationID);
		
		if(!existsPublication) {
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		publicationDAO.deleteById(publicationID);
	}
	
	public List<PublicationDomain> retrievePublicationList(String professionalID) throws IllegalArgumentException{
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professionalID);

		if(!existsProfessional){
			throw new IllegalArgumentException("Usário não encontrado");
		}
		
		List<PublicationDomain> publicationList = publicationDAO.findAllByProfessionalID(professionalID);
		publicationMapper.mapPublicationListToDescendingOrder(publicationList);
		
		return publicationList;
	}
	
	public List<PublicationDomain> retrieveFeedPublicationsList(String professionalID){
		
		List<ProfessionalDomain> listFriends = friendshipService.returnListFriends(professionalID);
		List<PublicationDomain> listPublications = new ArrayList<PublicationDomain>();
		for (ProfessionalDomain friend : listFriends) {
			listPublications.addAll(retrievePublicationList(friend.getProfessionalID()));
		}
		return publicationMapper.mapPublicationListToDescendingOrder(listPublications);
		
	}
	
	public PublicationReactionDomain reactToPublication(PublicationReactionDomain publicationReactionDomain) throws IllegalArgumentException{
		
		if (!professionalDAO.existsByProfessionalID(publicationReactionDomain.getProfessionalID())){
			throw new IllegalArgumentException("Usuário inexistente");
		}
		
		if(!publicationDAO.existsByPublicationID(publicationReactionDomain.getPublicationID())) {
			throw new IllegalArgumentException("Publicação inexistente");
		}
		
		if(publicationRectionDAO.existsByProfessionalIDAndPublicationID(publicationReactionDomain.getProfessionalID(),
				publicationReactionDomain.getPublicationID())) {
			throw new IllegalArgumentException("Publicação já curtida!");
		}
		
		publicationRectionDAO.insert(publicationReactionDomain);
		
		return publicationReactionDomain;
	}
	
	public void unreactToPublication(PublicationReactionDomain publicationReaction) throws IllegalArgumentException{
		if (!professionalDAO.existsByProfessionalID(publicationReaction.getProfessionalID())){
			throw new IllegalArgumentException("Usuário inexistente");
		}
		
		if(!publicationDAO.existsByPublicationID(publicationReaction.getPublicationID())) {
			throw new IllegalArgumentException("Publicação inexistente");
		}
		
		if(publicationRectionDAO.existsByProfessionalIDAndPublicationID(publicationReaction.getProfessionalID(),
				publicationReaction.getPublicationID())) {
			
			PublicationReactionDomain publicationReactionToBeDeleted = new PublicationReactionDomain();
			publicationReactionToBeDeleted = publicationRectionDAO.findByProfessionalIDAndPublicationID(publicationReaction.getProfessionalID(),
				publicationReaction.getPublicationID());
			publicationRectionDAO.delete(publicationReactionToBeDeleted);
			
		}else {
			throw new IllegalArgumentException("Curtida não encontrada");
		}
		
		
	}
	
	public int getNumberReactionsOfPublication(String publicationID) throws IllegalArgumentException{
		if(!publicationDAO.existsByPublicationID(publicationID)) {
			throw new IllegalArgumentException("Publicação inexistente");
		}
		
		return publicationRectionDAO.countByPublicationID(publicationID);
	}

	public List<ProfessionalDomain> getProfessionalsWhoRecommendedPublication(String publicationID) throws IllegalArgumentException{
		int numberOfPublications;
		
		try {
			numberOfPublications = getNumberReactionsOfPublication(publicationID);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Publicacao inexistente");
		}
		
		List<ProfessionalDomain> listProfessionalsWhoRecommendedPublication = new ArrayList<ProfessionalDomain>();
		
		for (PublicationReactionDomain publicationReaction : publicationRectionDAO.findAllByPublicationID(publicationID)) {
			listProfessionalsWhoRecommendedPublication.add(
					professionalDAO.findByProfessionalID(publicationReaction.getProfessionalID()));
		}
		
		return listProfessionalsWhoRecommendedPublication;
	}

	public int getStatusPublication(String professionalID, String publicationID) {
		
		if (!professionalDAO.existsByProfessionalID(professionalID)){
			throw new IllegalArgumentException("Usuário inexistente");
		}
		
		if(!publicationDAO.existsByPublicationID(publicationID)) {
			throw new IllegalArgumentException("Publicação inexistente");
		}
		
		if (publicationRectionDAO.existsByProfessionalIDAndPublicationID(professionalID, publicationID)) {
			return 1;
		}
		
		return 0;
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
