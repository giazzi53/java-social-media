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
		
		boolean existProfessional = professionalDAO.existsByProfessionalID(professionalID);
		
		if(!existProfessional) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		List<Professional_InterestTopicDomain> listProfessionalInterestTopics = professional_InterestTopicDAO.findByProfessionalID(professionalID);
		List<InterestTopicDomain> listInterestTopics = new ArrayList<InterestTopicDomain>();
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			listInterestTopics.add(interestTopicDAO.findByInterestTopicID(professionalInterestTopic.getInterestTopicID()));
		}
		
		return listInterestTopics;
	}
	
	public List<Professional_InterestTopicDomain> setProfessionalInterestTopics(List<Professional_InterestTopicDomain> listProfessionalInterestTopics) {
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			boolean existProfessional = professionalDAO.existsByProfessionalID(professionalInterestTopic.getProfessionalID());
			boolean existInterestTopic = interestTopicDAO.existsByInterestTopicID(professionalInterestTopic.getInterestTopicID());
			
			if(!existProfessional) {
				throw new IllegalArgumentException("Usuário não encontrado");
			}
			if(!existInterestTopic) {
				throw new IllegalArgumentException("Tópico de interesse não encontrado");
			}
		}
		
		String professionalID = listProfessionalInterestTopics.get(0).getProfessionalID();
		
		professional_InterestTopicDAO.deleteByProfessionalID(professionalID);
		
		for (Professional_InterestTopicDomain professionalInterestTopic : listProfessionalInterestTopics) {
			professional_InterestTopicDAO.insert(professionalInterestTopic);
		}
		
		return listProfessionalInterestTopics;
	}
	
}
