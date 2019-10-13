package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.DAO.RecommendationDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.RecommendationDomain;
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationDAO recommendationDAO;

	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private	ValidationUtils validationUtils;
	
	private static final int INACTIVE = 0;
	
	private static final int ACTIVE = 1;

	public RecommendationDomain recommend(RecommendationDomain recommendation) throws IllegalArgumentException {

		String recommenderID = recommendation.getRecommenderID();
		String recommendedID = recommendation.getRecommendedID();

		validationUtils.validateProfessionalByID(professionalDAO, recommenderID);
		validationUtils.validateProfessionalByID(professionalDAO, recommendedID);

		validationUtils.validateProfessionalsByEqualIDs(recommenderID, recommendedID);

		if (recommendationDAO.existsByRecommenderIDAndRecommendedID(recommenderID, recommendedID)) {

			throw new IllegalArgumentException("Você já recomendou esse usuário!");
		}

		recommendationDAO.insert(recommendation);

		return recommendation;
	}

	public Integer getNumberOfRecommendations(String recommendedID) throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, recommendedID);

		return recommendationDAO.countByRecommendedID(recommendedID);
	}

	public List<ProfessionalDomain> getProfessionalsWhoRecommended(String recommendedID)
			throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, recommendedID);

		List<ProfessionalDomain> professionalsWhoRecommendedList = new ArrayList<ProfessionalDomain>();

		for (RecommendationDomain recommendation : recommendationDAO.findByRecommendedID(recommendedID)) {

			professionalsWhoRecommendedList
					.add(professionalDAO.findByProfessionalID(recommendedID));
		}

		return professionalsWhoRecommendedList;
	}

	public int getRecommendationStatus(String recommenderID, String recommendedID) {

		validationUtils.validateProfessionalByID(professionalDAO, recommenderID);
		validationUtils.validateProfessionalByID(professionalDAO, recommendedID);

		validationUtils.validateProfessionalsByEqualIDs(recommenderID, recommendedID);

		if(recommendationDAO.existsByRecommenderIDAndRecommendedID(recommenderID, recommendedID)){
			
			return ACTIVE;
		}
		
		return INACTIVE;
	}

	public void deleteRecommendation(String recommenderID, String recommendedID) {

		validationUtils.validateProfessionalByID(professionalDAO, recommenderID);
		validationUtils.validateProfessionalByID(professionalDAO, recommendedID);

		validationUtils.validateProfessionalsByEqualIDs(recommenderID, recommendedID);

		if (!recommendationDAO.existsByRecommenderIDAndRecommendedID(recommenderID, recommendedID)) {
			
			throw new IllegalArgumentException("Recommendação não encontrada");
		}
		
		recommendationDAO.delete(
				recommendationDAO.findByRecommenderIDAndRecommendedID(recommenderID, recommendedID));
	}

}
