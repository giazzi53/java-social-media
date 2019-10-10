package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
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
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

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
	
	@Autowired
	private	ValidationUtils validationUtils;
	
	private final static int NOT_LIKED = 1;
	
	private final static int LIKED = 1;

	public PublicationDomain publicate(PublicationDomain publicationDomain) throws IllegalArgumentException{
		
		validationUtils.validateProfessionalByID(professionalDAO, publicationDomain.getProfessionalID());
		
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
	
	public List<PublicationDomain> retrievePublicationsList(String professionalID) throws IllegalArgumentException{
		
		validationUtils.validateProfessionalByID(professionalDAO, professionalID);
		
		List<PublicationDomain> publicationList = publicationDAO.findAllByProfessionalID(professionalID);
		
		publicationMapper.mapPublicationListToDescendingOrder(publicationList);
		
		return publicationList;
	}
	
	public List<PublicationDomain> retrieveFeedPublicationsList(String professionalID){
		
		List<ProfessionalDomain> freindsList = friendshipService.returnFriendsList(professionalID);
		List<PublicationDomain> publicationsList = new ArrayList<PublicationDomain>();
		
		for (ProfessionalDomain friend : freindsList) {
			
			publicationsList.addAll(retrievePublicationsList(friend.getProfessionalID()));
		}
		
		return publicationMapper.mapPublicationListToDescendingOrder(publicationsList);
	}
	
	public PublicationReactionDomain reactToPublication(PublicationReactionDomain publicationReactionDomain) throws IllegalArgumentException{
		
		validationUtils.validateProfessionalByID(professionalDAO, publicationReactionDomain.getProfessionalID());
		
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
		
		validationUtils.validateProfessionalByID(professionalDAO, publicationReaction.getProfessionalID());
		
		if(!publicationDAO.existsByPublicationID(publicationReaction.getPublicationID())) {
			
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		if(!publicationRectionDAO.existsByProfessionalIDAndPublicationID(publicationReaction.getProfessionalID(),
				publicationReaction.getPublicationID())) {
			
			throw new IllegalArgumentException("Curtida não encontrada");
		}

		PublicationReactionDomain publicationReactionToBeDeleted = new PublicationReactionDomain();
		
		publicationReactionToBeDeleted = publicationRectionDAO.findByProfessionalIDAndPublicationID(publicationReaction.getProfessionalID(),
			publicationReaction.getPublicationID());
		
		publicationRectionDAO.delete(publicationReactionToBeDeleted);
	}
	
	public int getNumberOfPublicationReactions(String publicationID) throws IllegalArgumentException{
		
		if(!publicationDAO.existsByPublicationID(publicationID)) {
			
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		return publicationRectionDAO.countByPublicationID(publicationID);
	}

	public List<String> getProfessionalsWhoReactedToPublication(String publicationID) throws IllegalArgumentException{
		
		if(!publicationDAO.existsByPublicationID(publicationID)) {
			
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		List<String> ProfessionalsWhoReactedToPublicationList = new ArrayList<String>();
		
		for (PublicationReactionDomain publicationReaction : publicationRectionDAO.findAllByPublicationID(publicationID)) {
			
			ProfessionalsWhoReactedToPublicationList.add(
					professionalDAO.findByProfessionalID(publicationReaction.getProfessionalID()).getProfessionalID());
		}
		
		return ProfessionalsWhoReactedToPublicationList;
	}

	public int getPublicationStatus(String professionalID, String publicationID) {
		
		if(!publicationDAO.existsByPublicationID(publicationID)) {
			
			throw new IllegalArgumentException("Publicação não encontrada");
		}
		
		validationUtils.validateProfessionalByID(professionalDAO, professionalID);
		
		if (publicationRectionDAO.existsByProfessionalIDAndPublicationID(professionalID, publicationID)) {
			
			return LIKED;
		}
		
		return NOT_LIKED;
		
	}

}
