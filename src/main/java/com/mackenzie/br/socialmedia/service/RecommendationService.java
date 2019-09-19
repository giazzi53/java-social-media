package com.mackenzie.br.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.RecommendationDAO;
import com.mackenzie.br.socialmedia.domain.RecommendationDomain;

@Service
public class RecommendationService {

	@Autowired
	RecommendationDAO recommendationDAO;
	
	@Autowired
	ProfessionalDAO professionalDAO;
	
	public RecommendationDomain recommend(RecommendationDomain recommendation) throws IllegalArgumentException{
		
		if (professionalDAO.existsByProfessionalID(recommendation.getProfessionalID1()) == false ||
				professionalDAO.existsByProfessionalID(recommendation.getProfessionalID2()) == false) {
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		if (recommendation.getProfessionalID1().equalsIgnoreCase(recommendation.getProfessionalID2())) {
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		if (recommendationDAO.existsByProfessionalID1AndProfessionalID2(recommendation.getProfessionalID1(),
				recommendation.getProfessionalID2())) {
			throw new IllegalArgumentException("Você já recomendou esse usuário!");
		}
		
		recommendationDAO.insert(recommendation);
		
		return recommendation;
	}
	
	public Integer getNumberOfRecommendations(String professionalID) throws IllegalArgumentException{
		
		if (!professionalDAO.existsByProfessionalID(professionalID)) {
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		return recommendationDAO.countByProfessionalID2(professionalID);
	}

}
