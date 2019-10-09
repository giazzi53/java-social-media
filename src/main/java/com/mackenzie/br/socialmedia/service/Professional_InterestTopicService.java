package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.InterestTopicDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.Professional_InterestTopicDAO;
import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;
import com.mackenzie.br.socialmedia.domain.Professional_InterestTopicDomain;
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

@Service
public class Professional_InterestTopicService {
	
	@Autowired
	private Professional_InterestTopicDAO professional_InterestTopicDAO;
	
	@Autowired
	private InterestTopicDAO interestTopicDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private	ValidationUtils validationUtils;	

	public List<InterestTopicDomain> getProfessionalInterestTopics(String professionalID) {
		
		validationUtils.validateProfessionalByID(professionalDAO, professionalID);
		
		List<Professional_InterestTopicDomain> listProfessionalInterestTopics = professional_InterestTopicDAO.findByProfessionalID(professionalID);
		List<InterestTopicDomain> interestTopicsList = new ArrayList<InterestTopicDomain>();
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			
			interestTopicsList.add(interestTopicDAO.findByInterestTopicID(professionalInterestTopic.getInterestTopicID()));
		}
		
		return interestTopicsList;
	}
	
	public List<Professional_InterestTopicDomain> setProfessionalInterestTopics(List<Professional_InterestTopicDomain> professionalInterestTopicsList) {
		
		for (Professional_InterestTopicDomain professionalInterestTopic : professionalInterestTopicsList) {
			
			boolean existsInterestTopic = interestTopicDAO.existsByInterestTopicID(professionalInterestTopic.getInterestTopicID());
			
			validationUtils.validateProfessionalByID(professionalDAO, professionalInterestTopic.getProfessionalID());

			if(!existsInterestTopic) {
			
				throw new IllegalArgumentException("Tópico de interesse não encontrado");
			}
		}
		
		String professionalID = professionalInterestTopicsList.get(0).getProfessionalID();
		
		professional_InterestTopicDAO.deleteByProfessionalID(professionalID);
		
		for (Professional_InterestTopicDomain professionalInterestTopic : professionalInterestTopicsList) {
			
			professional_InterestTopicDAO.insert(professionalInterestTopic);
		}
		
		return professionalInterestTopicsList;
	}
	
}
