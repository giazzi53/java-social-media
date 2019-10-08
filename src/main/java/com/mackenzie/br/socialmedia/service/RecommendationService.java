package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.RecommendationDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.RecommendationDomain;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationDAO recommendationDAO;
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	public RecommendationDomain recommend(RecommendationDomain recommendation) throws IllegalArgumentException{
		
		if (professionalDAO.existsByProfessionalID(recommendation.getProfessionalID1()) == false ||
				professionalDAO.existsByProfessionalID(recommendation.getProfessionalID2()) == false) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
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
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		return recommendationDAO.countByProfessionalID2(professionalID);
	}

	public List<ProfessionalDomain> getProfessionalsWhoRecommended(String professionalID) throws IllegalArgumentException{
		
		if (!professionalDAO.existsByProfessionalID(professionalID)) {
			
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		List<ProfessionalDomain> professionalsWhoRecommendedList = new ArrayList<ProfessionalDomain>();
		
		for (RecommendationDomain recommendation : recommendationDAO.findByProfessionalID2(professionalID)) {
			
			professionalsWhoRecommendedList.add(professionalDAO.findByProfessionalID(recommendation.getProfessionalID1()));
		}
		
		return professionalsWhoRecommendedList;
	}

	public int getRecommendationStatus(String professionalID1, String professionalID2) {
		
		if (professionalDAO.existsByProfessionalID(professionalID1) == false ||
				professionalDAO.existsByProfessionalID(professionalID2) == false) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		if (professionalID1.equalsIgnoreCase(professionalID2)) {
			
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
//		if(recommendationDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2)){
//			
//			return 1;
//		}
//		
//		return 0;
		return 0;
	}
	
	public void deleteRecommendation(String professionalID1, String professionalID2) {
		
		if (professionalDAO.existsByProfessionalID(professionalID1) == false ||
				professionalDAO.existsByProfessionalID(professionalID2) == false) {
			
			throw new IllegalArgumentException("Usuário não encontardo");
		}
		
		if (professionalID1.equalsIgnoreCase(professionalID2)) {
			
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		if (recommendationDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2)) {
			
			recommendationDAO.delete(recommendationDAO.findByProfessionalID1AndProfessionalID2(professionalID1,professionalID2));
		}
		
		throw new IllegalArgumentException("Recommendação não encontrada");
	}

}
