package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Repository
public interface FriendshipRequestDAO extends MongoRepository<FriendshipRequestDomain, String>{
	
	List<FriendshipRequestDomain> findByProfessionalID1(String professionalID1);
	
	List<FriendshipRequestDomain> findByProfessionalID2(String professionalID2);
	
	boolean existsByProfessionalID1AndProfessionalID2 (String profesisonalID1, String professionalID2);
	
	boolean existsByProfessionalID2AndProfessionalID1 (String profesisonalID2, String professionalID1);

}
