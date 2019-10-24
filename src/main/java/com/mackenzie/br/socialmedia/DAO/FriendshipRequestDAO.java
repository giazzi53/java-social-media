package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;

@Repository
public interface FriendshipRequestDAO extends MongoRepository<FriendshipRequestDomain, String>{
	
	FriendshipRequestDomain findByRequestSenderID (String requestSenderID);
	
	FriendshipRequestDomain findByRequestReceiverID (String requestReceiverID);
	
	List<FriendshipRequestDomain> findAllByRequestSenderID (String requestSenderID);
	
	List<FriendshipRequestDomain> findAllByRequestReceiverID (String requestReceiverID);
	
	boolean existsByRequestSenderIDAndRequestReceiverID (String requestSenderID, String requestReceiverID);
	
	boolean existsByRequestReceiverIDAndRequestSenderID (String requestReceiverID, String requestSenderID);

	FriendshipRequestDomain findByRequestSenderIDAndRequestReceiverID(String requestSenderID, String requestReceiverID);

}
