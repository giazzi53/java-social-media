package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.Professional_InterestTopicDomain;

@Repository
public interface Professional_InterestTopicDAO extends MongoRepository<Professional_InterestTopicDomain, String>{
	
	Professional_InterestTopicDomain findByProfessionalID (String professionalID);
	
	boolean existsByProfessionalID (String professionalID);

	void deleteByProfessionalID(String professionalID);
	
}