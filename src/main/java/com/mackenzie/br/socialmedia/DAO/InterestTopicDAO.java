package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;

@Repository
public interface InterestTopicDAO extends MongoRepository<InterestTopicDomain, String>{
	
	
}
