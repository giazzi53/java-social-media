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

	public RecommendationDomain recommend(RecommendationDomain recommendation) throws IllegalArgumentException {

		String recommenderID = recommendation.getRecommenderID();
		String recommendedID = recommendation.getRecommendedID();

		boolean existsRecommender = professionalDAO.existsByProfessionalID(recommenderID);
		boolean existsRecommended = professionalDAO.existsByProfessionalID(recommendedID);

		if (!(existsRecommender & existsRecommended)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		if (recommenderID.equalsIgnoreCase(recommendedID)) {

			throw new IllegalArgumentException("Os IDs são iguais");
		}

		if (recommendationDAO.existsByProfessionalID1AndProfessionalID2(recommenderID, recommendedID)) {

			throw new IllegalArgumentException("Você já recomendou esse usuário!");
		}

		recommendationDAO.insert(recommendation);

		return recommendation;
	}

	public Integer getNumberOfRecommendations(String recommendedID) throws IllegalArgumentException {

		if (!professionalDAO.existsByProfessionalID(recommendedID)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		return recommendationDAO.countByProfessionalID2(recommendedID);
	}

	public List<ProfessionalDomain> getProfessionalsWhoRecommended(String recommendedID)
			throws IllegalArgumentException {

		if (!professionalDAO.existsByProfessionalID(recommendedID)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		List<ProfessionalDomain> professionalsWhoRecommendedList = new ArrayList<ProfessionalDomain>();

		for (RecommendationDomain recommendation : recommendationDAO.findByProfessionalID2(recommendedID)) {

			professionalsWhoRecommendedList
					.add(professionalDAO.findByProfessionalID(recommendedID));
		}

		return professionalsWhoRecommendedList;
	}

	public int getRecommendationStatus(String recommenderID, String recommendedID) {

		boolean existsRecommender = professionalDAO.existsByProfessionalID(recommenderID);
		boolean existsRecommended = professionalDAO.existsByProfessionalID(recommendedID);

		if (!(existsRecommender & existsRecommended)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		if (recommenderID.equalsIgnoreCase(recommendedID)) {

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

	public void deleteRecommendation(String recommenderID, String recommendedID) {

		boolean existsRecommender = professionalDAO.existsByProfessionalID(recommenderID);
		boolean existsRecommended = professionalDAO.existsByProfessionalID(recommendedID);

		if (!(existsRecommender & existsRecommended)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		if (recommenderID.equalsIgnoreCase(recommendedID)) {

			throw new IllegalArgumentException("Os IDs são iguais");
		}

		if (!recommendationDAO.existsByProfessionalID1AndProfessionalID2(recommenderID, recommendedID)) {
			
			throw new IllegalArgumentException("Recommendação não encontrada");

		}
		
		recommendationDAO.delete(
				recommendationDAO.findByProfessionalID1AndProfessionalID2(recommenderID, recommendedID));
	}

}
