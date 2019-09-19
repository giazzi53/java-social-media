package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.RecommendationDomain;

@Repository
public interface RecommendationDAO extends MongoRepository<RecommendationDomain, String>{
	
	boolean existsByProfessionalID1AndProfessionalID2(String professionalID1, String professionalID2);

	Integer countByProfessionalID2(String professionalID2);
}
