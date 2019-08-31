package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@Repository
public interface FriendshipDAO extends MongoRepository<PublicationDomain, String>{
	
}
