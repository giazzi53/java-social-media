package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.Professional_InterestTopicDAO;
import com.mackenzie.br.socialmedia.domain.Professional_InterestTopicDomain;

@Service
public class Professional_InterestTopicService {
	
	@Autowired
	private Professional_InterestTopicDAO professional_InterestTopicDAO;

	public List<Professional_InterestTopicDomain> getProfessionalInterestTopics(String professionalID) {
		boolean existsProfessional_InterestTopicDomain = professional_InterestTopicDAO.existsByProfessionalID(professionalID);
		
		if(!existsProfessional_InterestTopicDomain) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		return professional_InterestTopicDAO.findByProfessionalID(professionalID);
	}
}
