package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.RecommendationDomain;

@Repository
public interface RecommendationDAO extends MongoRepository<RecommendationDomain, String>{
	
	boolean existsByRecommenderIDAndRecommendedID(String recommenderID, String recommendedID);

	int countByRecommendedID(String recommendedID);
	
	List<RecommendationDomain> findAllByRecommendedID(String recommendedID);
	
	RecommendationDomain findByRecommenderIDAndRecommendedID(String recommenderID, String recommendedID);

}
