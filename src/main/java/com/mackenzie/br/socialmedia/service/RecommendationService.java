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

	public List<ProfessionalDomain> getProfessionalsWhoRecommended(String professionalID) throws IllegalArgumentException{
		
		if (!professionalDAO.existsByProfessionalID(professionalID)) {
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		List<ProfessionalDomain> listProfessionalWhoRecommended = new ArrayList<ProfessionalDomain>();
		
		for (RecommendationDomain recommendation : recommendationDAO.findByProfessionalID2(professionalID)) {
			listProfessionalWhoRecommended.add(professionalDAO.findByProfessionalID(recommendation.getProfessionalID1()));
		}
		
		return listProfessionalWhoRecommended;
	}

	public int getStatusRecommendation(List<ProfessionalDomain> listProfessionals) {
		
		if (professionalDAO.existsByProfessionalID(listProfessionals.get(0).getProfessionalID()) == false ||
				professionalDAO.existsByProfessionalID(listProfessionals.get(1).getProfessionalID()) == false) {
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		if (listProfessionals.get(0).getProfessionalID().equalsIgnoreCase(listProfessionals.get(1).getProfessionalID())) {
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(listProfessionals.get(0).getProfessionalID());
		ProfessionalDomain professional1 = professionalDAO.findByProfessionalID(listProfessionals.get(1).getProfessionalID());
		
		if(recommendationDAO.existsByProfessionalID1AndProfessionalID2(professional0.getProfessionalID(), professional1.getProfessionalID())){
			return 1;
		}
		return 0;
	}
	
	public void deleteRecommendation(List<ProfessionalDomain> listProfessionals) {
		
		if (professionalDAO.existsByProfessionalID(listProfessionals.get(0).getProfessionalID()) == false ||
				professionalDAO.existsByProfessionalID(listProfessionals.get(1).getProfessionalID()) == false) {
			throw new IllegalArgumentException("Usuário não válido");
		}
		
		if (listProfessionals.get(0).getProfessionalID().equalsIgnoreCase(listProfessionals.get(1).getProfessionalID())) {
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(listProfessionals.get(0).getProfessionalID());
		ProfessionalDomain professional1 = professionalDAO.findByProfessionalID(listProfessionals.get(1).getProfessionalID());
		
		if (recommendationDAO.existsByProfessionalID1AndProfessionalID2(professional0.getProfessionalID(), professional1.getProfessionalID())) {
			recommendationDAO.delete(recommendationDAO.findByProfessionalID1AndProfessionalID2(professional0.getProfessionalID(),professional1.getProfessionalID()));
		}
		throw new IllegalArgumentException("Recommendação não encontrada");
		
	}

}
