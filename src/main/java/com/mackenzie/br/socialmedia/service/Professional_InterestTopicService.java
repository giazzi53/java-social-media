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

@Service
public class Professional_InterestTopicService {
	
	@Autowired
	private Professional_InterestTopicDAO professional_InterestTopicDAO;
	
	@Autowired
	private InterestTopicDAO interestTopicDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;

	public List<InterestTopicDomain> getProfessionalInterestTopics(String professionalID) {
		
		boolean existsProfessional_InterestTopicDomain = professional_InterestTopicDAO.existsByProfessionalID(professionalID);
		
		if(!existsProfessional_InterestTopicDomain) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		List<Professional_InterestTopicDomain> listProfessionalInterestTopics = professional_InterestTopicDAO.findByProfessionalID(professionalID);
		List<InterestTopicDomain> listInterestTopics = new ArrayList<InterestTopicDomain>();
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			listInterestTopics.add(interestTopicDAO.findByInterestTopicID(professionalInterestTopic.getInterestTopicID()));
		}
		
		return listInterestTopics;
	}
	
	public List<Professional_InterestTopicDomain> updateProfessionalInterestTopics(List<Professional_InterestTopicDomain> listProfessionalInterestTopics) {
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			boolean existsProfessional_InterestTopicDomain = professionalDAO.existsByProfessionalID(professionalInterestTopic.getProfessionalID());
			if(!existsProfessional_InterestTopicDomain) {
				throw new IllegalArgumentException("Usuário não encontrado");
			}
			professional_InterestTopicDAO.insert(professionalInterestTopic);
			
		}
		
		return listProfessionalInterestTopics;
	}
	
}
