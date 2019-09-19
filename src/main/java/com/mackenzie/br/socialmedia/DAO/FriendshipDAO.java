package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;

@Repository
public interface FriendshipDAO extends MongoRepository<FriendshipDomain, String>{
	
	List<FriendshipDomain> findByProfessionalID1(String professionalID1);
	
	List<FriendshipDomain> findByProfessionalID2(String professionalID2);
	
	Long countByProfessionalID1OrProfessionalID2 (String profesisonalID1, String professionalID2);
	
	boolean existsByProfessionalID1AndProfessionalID2 (String profesisonalID1, String professionalID2);
	
	boolean existsByProfessionalID2AndProfessionalID1 (String profesisonalID2, String professionalID1);
}
