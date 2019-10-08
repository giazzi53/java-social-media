package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.FriendshipRequestDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.enums.FriendshipStatusEnum;
import com.mackenzie.br.socialmedia.map.FriendshipMapper;

@Service
public class FriendshipService {

	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private FriendshipDAO friendshipDAO;
	
	@Autowired
	private FriendshipRequestDAO friendshipRequestDAO;
	
	@Autowired
	private FriendshipMapper friendshipMapper;
	
	@Autowired
	private FriendshipRequestDomain friendshipRequest;
	
	@Autowired 
	private FriendshipDomain friendshipDomain;
	
	public FriendshipRequestDomain sendFriendshipRequest(List<String> professionalIDs) throws IllegalArgumentException {
		
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionalIDs.get(0));
		boolean existsProfessional2 = professionalDAO.existsByProfessionalID(professionalIDs.get(1));
		
		if (!(existsProfessional1 && existsProfessional2)) {
		
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		List<String> friendsIDsList = friendshipMapper.mapFriendsIDsList(professionalIDs.get(0),
				friendshipDAO.findByProfessionalID1(professionalIDs.get(0)), 
				friendshipDAO.findByProfessionalID2(professionalIDs.get(0)));
		
		for (String friendID : friendsIDsList) {
			
			if (friendID.equalsIgnoreCase(professionalIDs.get(0))) {
				
				throw new IllegalArgumentException("Este usuário já é seu amigo");
			}
		}
		
		if (professionalIDs.get(0).equalsIgnoreCase(professionalIDs.get(1))) {
			
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		List<FriendshipRequestDomain> sentRequestsList = friendshipRequestDAO.findAllByProfessionalID1(professionalIDs.get(0));
		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO.findAllByProfessionalID2(professionalIDs.get(1));
		
		for (FriendshipRequestDomain request : sentRequestsList){
			
			if (request.getProfessionalID2().equalsIgnoreCase(professionalIDs.get(0))) {
				
				throw new IllegalArgumentException("A solicitação já existe");
			}
		}
		
		for (FriendshipRequestDomain request : receivedRequestsList){
			
			if (request.getProfessionalID2().equalsIgnoreCase(professionalIDs.get(0))) {
				
				throw new IllegalArgumentException("Não pode mandar a solicitação, o profissional já está esperando sua resposta");
				
			}
		}
		
		friendshipRequest.setProfessionalID1(professionalIDs.get(0));
		friendshipRequest.setProfessionalID2(professionalIDs.get(1));
		friendshipRequestDAO.insert(friendshipRequest);
		
		return friendshipRequest;
	}

	public FriendshipDomain acceptFriendshipRequest(List<String> professionalIDs) throws IllegalArgumentException{
		
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionalIDs.get(0));
		boolean existsProfessional2 = professionalDAO.existsByProfessionalID(professionalIDs.get(1));
		
		if (!(existsProfessional1 && existsProfessional2)) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		if (professionalIDs.get(0).equalsIgnoreCase(professionalIDs.get(0))) {
			
			throw new IllegalArgumentException("IDs são iguais");
		}
		
		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO.findAllByProfessionalID2(professionalIDs.get(0));
		
		List<String> friendsIDsList = friendshipMapper.mapFriendsIDsList(professionalIDs.get(0),
				friendshipDAO.findByProfessionalID1(professionalIDs.get(0)), 
				friendshipDAO.findByProfessionalID2(professionalIDs.get(0)));
		
		for (String friendID : friendsIDsList) {
			
			if (friendID.equalsIgnoreCase(professionalIDs.get(1))) {
				
				throw new IllegalArgumentException("Usuário já é seu amigo");
			}
		}
		
		for (FriendshipRequestDomain request : receivedRequestsList){
			
			if (request.getProfessionalID1().equalsIgnoreCase(professionalIDs.get(1))) {
				
				friendshipDomain.setProfessionalID1(professionalIDs.get(0));
				friendshipDomain.setProfessionalID2(professionalIDs.get(1));
				friendshipRequestDAO.delete(request);
				friendshipDAO.save(friendshipDomain);
				
				return friendshipDomain;
			}
		}
		
		throw new IllegalArgumentException("Solicitação não encontrada");
	}
	
	public FriendshipRequestDomain rejectFriendshipRequest(List<String> professionalIDs) throws IllegalArgumentException{
		
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionalIDs.get(0));
		boolean existsProfessional2 = professionalDAO.existsByProfessionalID(professionalIDs.get(1));
		
		if (!(existsProfessional1 && existsProfessional2)) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		if (professionalIDs.get(0).equalsIgnoreCase(professionalIDs.get(1))) {
			
			throw new IllegalArgumentException("IDs são iguais");
		}
		
		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO.findAllByProfessionalID2(professionalIDs.get(1));
		
		for (FriendshipRequestDomain request : receivedRequestsList){
			
			if (request.getProfessionalID1().equalsIgnoreCase(professionalIDs.get(0))) {
				
				friendshipRequest.setFriendshipRequestId(request.getFriendshipRequestId());
				friendshipRequest.setProfessionalID1(request.getProfessionalID1());
				friendshipRequest.setProfessionalID2(request.getProfessionalID2());
				friendshipRequestDAO.delete(request);
				
				return friendshipRequest;	
			}
		}
		
		throw new IllegalArgumentException("Request not found");
	}

	public List<ProfessionalDomain> returnFriendsList(String professionalID) throws IllegalArgumentException{
		
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professionalID);

		if(!existsProfessional) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		
		List<String> listFriendsID = friendshipMapper.mapFriendsIDsList(professionalID,
				friendshipDAO.findByProfessionalID1(professionalID), 
				friendshipDAO.findByProfessionalID2(professionalID));
		
		List<ProfessionalDomain> friendsList = new ArrayList<ProfessionalDomain>();
		
		for (String id : listFriendsID) {
			
			friendsList.add(professionalDAO.findByProfessionalID(id));
		}
		
		return friendsList;
	}
	
	public FriendshipStatusEnum getFriendshipStatus(String professionalID1, String professionalID2) throws IllegalArgumentException{
		
		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionalID1);
		boolean existsProfessional2 = professionalDAO.existsByProfessionalID(professionalID2);
		
		if (!(existsProfessional1 && existsProfessional2)) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		if (professionalID1.equalsIgnoreCase(professionalID2)) {
			
			throw new IllegalArgumentException("Os IDs são iguais");
		}
		
		if (friendshipDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2) ||
				friendshipDAO.existsByProfessionalID2AndProfessionalID1(professionalID1, professionalID2)) {
			
			return FriendshipStatusEnum.ATIVO;
		}
		
		if(friendshipRequestDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2)) {
			
			return FriendshipStatusEnum.PENDENTE_SOLICITACAO;
		}
		
		if(friendshipRequestDAO.existsByProfessionalID2AndProfessionalID1(professionalID1, professionalID2)) {
			
			return FriendshipStatusEnum.PENDENTE_RESPOSTA;
		}
		
		return FriendshipStatusEnum.INATIVO;
	}

	public List<String> getFriendsInCommon(String professionalID1, String professionalID2) {
		
		List<ProfessionalDomain> professional1friendList = returnFriendsList(professionalID1);
		List<ProfessionalDomain> professional2friendList = returnFriendsList(professionalID2);
		List<String> friendsInCommon = new ArrayList<String>();
	
		for(ProfessionalDomain professional1 : professional1friendList) {
			
			for(ProfessionalDomain professional2 : professional2friendList) {
				
				if(professional1.getProfessionalID().equals(professional2.getProfessionalID())) {
					
					friendsInCommon.add(professional1.getProfessionalID());
				}
			}
		}
		
		return friendsInCommon;
	}

}
