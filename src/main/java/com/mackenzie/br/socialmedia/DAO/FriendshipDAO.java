package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;

@Repository
public interface FriendshipDAO extends MongoRepository<FriendshipDomain, String>{
	
	List<FriendshipDomain> findByProfessionalID1(String professionalID1);
	
	List<FriendshipDomain> findByProfessionalID2(String professionalID2);
}
