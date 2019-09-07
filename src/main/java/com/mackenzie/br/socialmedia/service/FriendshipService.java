package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.FriendshipRequestDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.map.FriendshipMapper;

@Service
public class FriendshipService {
	
	public FriendshipService() {
		super();
	}

	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private FriendshipDAO friendshipDAO;
	
	@Autowired
	private FriendshipRequestDAO friendshipRequestDAO;
	
	@Autowired
	private FriendshipMapper friendshipMapper;
	
	public FriendshipRequestDomain sendFriendshipRequest(List<ProfessionalDomain> professionals) throws IllegalArgumentException {
		
		boolean existsProfessional0 = professionalDAO.existsByProfessionalID(professionals.get(0).getProfessionalID());
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionals.get(1).getProfessionalID());
		
		if (existsProfessional0 && existsProfessional1) {
		}else {
			throw new IllegalArgumentException("User not found");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(professionals.get(0).getProfessionalID());
		ProfessionalDomain professional1 = professionalDAO.findByProfessionalID(professionals.get(1).getProfessionalID());
		
		List<String> listFriendsID = friendshipMapper.mapListFriendsId(professional0.getProfessionalID(),
				friendshipDAO.findByProfessionalID1(professional0.getProfessionalID()), 
				friendshipDAO.findByProfessionalID2(professional0.getProfessionalID()));
		
		for (String friendID : listFriendsID) {
			if (friendID.equalsIgnoreCase(professional1.getProfessionalID())) {
				throw new IllegalArgumentException("User is already your friend");
			}
		}
		
		if (professional0.getProfessionalID().equalsIgnoreCase(professional1.getProfessionalID()) ) {
			throw new IllegalArgumentException("IDs are equal");
		}
		
		List<FriendshipRequestDomain> listRequestSent = friendshipRequestDAO.findByProfessionalID1(professional0.getProfessionalID());
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.getProfessionalID());
		
		for (FriendshipRequestDomain request : listRequestSent){
			if (request.getProfessionalID2().equalsIgnoreCase(professional1.getProfessionalID())) {
				throw new IllegalArgumentException("Request already exists");
			}
		}
		
		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID2().equalsIgnoreCase(professional0.getProfessionalID())) {
				throw new IllegalArgumentException("Can`t send request, the other professional is already waiting for your response");
				
			}
		}
		
		FriendshipRequestDomain friendshipRequest = new FriendshipRequestDomain();
		friendshipRequest.setProfessionalID1(professional0.getProfessionalID());
		friendshipRequest.setProfessionalID2(professional1.getProfessionalID());
		friendshipRequestDAO.insert(friendshipRequest);
		
		
		return friendshipRequest;
	}

	public FriendshipDomain acceptFriendshipRequest(List<ProfessionalDomain> professionals) throws IllegalArgumentException{
		
		boolean existsProfessional0 = professionalDAO.existsByProfessionalID(professionals.get(0).getProfessionalID());
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionals.get(1).getProfessionalID());
		
		if (existsProfessional0 && existsProfessional1) {
		}else {
			throw new IllegalArgumentException("User not found");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(professionals.get(0).getProfessionalID());
		ProfessionalDomain professional1 = professionalDAO.findByProfessionalID(professionals.get(1).getProfessionalID());
		
		if (professional0.getProfessionalID().equalsIgnoreCase(professional1.getProfessionalID()) ) {
			throw new IllegalArgumentException("IDs are equal");
		}
		
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.getProfessionalID());
		
		List<String> listFriendsID = friendshipMapper.mapListFriendsId(professional0.getProfessionalID(),
				friendshipDAO.findByProfessionalID1(professional0.getProfessionalID()), 
				friendshipDAO.findByProfessionalID2(professional0.getProfessionalID()));
		
		for (String friendID : listFriendsID) {
			if (friendID.equalsIgnoreCase(professional1.getProfessionalID())) {
				throw new IllegalArgumentException("User is already your friend");
			}
		}
		
		FriendshipDomain friendship =  new FriendshipDomain();
		
		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID1().equalsIgnoreCase(professional1.getProfessionalID())) {
				
				friendship.setProfessionalID1(professional0.getProfessionalID());
				friendship.setProfessionalID2(professional1.getProfessionalID());
				
				friendshipRequestDAO.delete(request);
				friendshipDAO.save(friendship);
				return friendship;
			}
		}
		throw new IllegalArgumentException("Request not found");
		
	}
	
	public FriendshipRequestDomain rejectFriendshipRequest(List<ProfessionalDomain> professionals) throws IllegalArgumentException{
		
		boolean existsProfessional0 = professionalDAO.existsByProfessionalID(professionals.get(0).getProfessionalID());
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionals.get(1).getProfessionalID());
		
		if (existsProfessional0 && existsProfessional1) {
		}else {
			throw new IllegalArgumentException("User not found");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(professionals.get(0).getProfessionalID());
		ProfessionalDomain professional1 = professionalDAO.findByProfessionalID(professionals.get(1).getProfessionalID());
		
		if (professional0.getProfessionalID().equalsIgnoreCase(professional1.getProfessionalID()) ) {
			throw new IllegalArgumentException("IDs are equal");
		}
		
		List<FriendshipRequestDomain> listRequestReceived = friendshipRequestDAO.findByProfessionalID2(professional0.getProfessionalID());
		
		for (FriendshipRequestDomain request : listRequestReceived){
			if (request.getProfessionalID1().equalsIgnoreCase(professional1.getProfessionalID())) {
				FriendshipRequestDomain requestDeleted = new FriendshipRequestDomain();
				requestDeleted.setFriendshipRequestId(request.getFriendshipRequestId());
				requestDeleted.setProfessionalID1(request.getProfessionalID1());
				requestDeleted.setProfessionalID2(request.getProfessionalID2());
				friendshipRequestDAO.delete(request);
				return requestDeleted;
				
			}
		}
		
		throw new IllegalArgumentException("Request not found");
	}

	public List<ProfessionalDomain> returnListFriends(ProfessionalDomain professional) throws IllegalArgumentException{
		
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professional.getProfessionalID());

		if(!existsProfessional) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		ProfessionalDomain professional0 = professionalDAO.findByProfessionalID(professional.getProfessionalID());
		
		List<String> listFriendsID = friendshipMapper.mapListFriendsId(professional0.getProfessionalID(),
				friendshipDAO.findByProfessionalID1(professional0.getProfessionalID()), 
				friendshipDAO.findByProfessionalID2(professional0.getProfessionalID()));
		
		List<ProfessionalDomain> listFriends = new ArrayList<ProfessionalDomain>();
		
		for( String id : listFriendsID) {
			listFriends.add(professionalDAO.findByProfessionalID(id));
		}
		
		return listFriends;
	}

}
