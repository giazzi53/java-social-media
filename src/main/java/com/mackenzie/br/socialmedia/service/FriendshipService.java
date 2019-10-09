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
import com.mackenzie.br.socialmedia.domain.FriendshipSenderReceiverDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
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

	private static final int ATIVO = 1;

	private static final int PENDENTE_SOLICITACAO = 2;

	private static final int PENDENTE_RESPOSTA = 3;

	private static final int INATIVO = 4;

	public FriendshipRequestDomain sendFriendshipRequest(FriendshipSenderReceiverDomain senderReceiver) throws IllegalArgumentException {

		String senderID = senderReceiver.getSenderID();
		String receiverID = senderReceiver.getReceiverID();
		
		boolean existsSender = professionalDAO.existsByProfessionalID(senderID);
		boolean existsReceiver = professionalDAO.existsByProfessionalID(receiverID);

		if (!(existsSender && existsReceiver)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		List<String> friendsIDsList = friendshipMapper.mapFriendsIDsList(senderID,
				friendshipDAO.findByProfessionalID1(senderID),
				friendshipDAO.findByProfessionalID2(senderID));

		for (String friendID : friendsIDsList) {

			if (friendID.equalsIgnoreCase(senderID)) {

				throw new IllegalArgumentException("Este usuário já é seu amigo");
			}
		}

		if (senderID.equalsIgnoreCase(receiverID)) {

			throw new IllegalArgumentException("Os IDs são iguais");
		}

		List<FriendshipRequestDomain> sentRequestsList = friendshipRequestDAO
				.findAllByRequestSenderID(senderID);
		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO
				.findAllByRequestReceiverID(receiverID);

		for (FriendshipRequestDomain request : sentRequestsList) {

			if (request.getRequestReceiverID().equalsIgnoreCase(senderID)) {

				throw new IllegalArgumentException("A solicitação já existe");
			}
		}

		for (FriendshipRequestDomain request : receivedRequestsList) {

			if (request.getRequestReceiverID().equalsIgnoreCase(senderID)) {

				throw new IllegalArgumentException(
						"Não pode mandar a solicitação, o profissional já está esperando sua resposta");

			}
		}

		FriendshipRequestDomain friendshipRequest = new FriendshipRequestDomain();
		
		friendshipRequest.setRequestSenderID(senderID);
		friendshipRequest.setRequestReceiverID(receiverID);
		friendshipRequestDAO.insert(friendshipRequest);

		return friendshipRequest;
	}

	public FriendshipDomain acceptFriendshipRequest(FriendshipSenderReceiverDomain senderReceiver) throws IllegalArgumentException {

		String senderID = senderReceiver.getSenderID();
		String receiverID = senderReceiver.getReceiverID();
		
		boolean existsSender = professionalDAO.existsByProfessionalID(senderID);
		boolean existsReceiver = professionalDAO.existsByProfessionalID(receiverID);

		if (!(existsSender && existsReceiver)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		if (senderID.equalsIgnoreCase(senderID)) {

			throw new IllegalArgumentException("IDs são iguais");
		}

		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO
				.findAllByRequestReceiverID(senderID);

		List<String> friendsIDsList = friendshipMapper.mapFriendsIDsList(senderID,
				friendshipDAO.findByProfessionalID1(senderID),
				friendshipDAO.findByProfessionalID2(senderID));

		for (String friendID : friendsIDsList) {

			if (friendID.equalsIgnoreCase(receiverID)) {

				throw new IllegalArgumentException("Usuário já é seu amigo");
			}
		}

		for (FriendshipRequestDomain request : receivedRequestsList) {

			if (request.getRequestSenderID().equalsIgnoreCase(receiverID)) {
				
				FriendshipDomain friendshipDomain = new FriendshipDomain();

				friendshipDomain.setProfessionalID1(senderID);
				friendshipDomain.setProfessionalID2(receiverID);
				friendshipRequestDAO.delete(request);
				friendshipDAO.save(friendshipDomain);

				return friendshipDomain;
			}
		}

		throw new IllegalArgumentException("Solicitação não encontrada");
	}

	public FriendshipRequestDomain rejectFriendshipRequest(FriendshipSenderReceiverDomain senderReceiver)
			throws IllegalArgumentException {
		
		String senderID = senderReceiver.getSenderID();
		String receiverID = senderReceiver.getReceiverID();

		boolean existsSender = professionalDAO.existsByProfessionalID(senderID);
		boolean existsReceiver = professionalDAO.existsByProfessionalID(receiverID);

		if (!(existsSender && existsReceiver)) {

			throw new IllegalArgumentException("Usuário não encontrado");
		}

		if (senderID.equalsIgnoreCase(receiverID)) {

			throw new IllegalArgumentException("IDs são iguais");
		}

		List<FriendshipRequestDomain> receivedRequestsList = friendshipRequestDAO
				.findAllByRequestReceiverID(receiverID);

		for (FriendshipRequestDomain request : receivedRequestsList) {

			if (request.getRequestSenderID().equalsIgnoreCase(senderID)) {
				
				FriendshipRequestDomain friendshipRequest = new FriendshipRequestDomain();

				//friendshipRequest.setFriendshipRequestID(request.getFriendshipRequestID());
				friendshipRequest.setRequestSenderID(request.getRequestSenderID());
				friendshipRequest.setRequestReceiverID(request.getRequestReceiverID());
				friendshipRequestDAO.delete(request);

				return friendshipRequest;
			}
		}

		throw new IllegalArgumentException("Request not found");
	}

	public List<ProfessionalDomain> returnFriendsList(String professionalID) throws IllegalArgumentException {

		boolean existsProfessional = professionalDAO.existsByProfessionalID(professionalID);

		if (!existsProfessional) {

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

//	public int getFriendshipStatus(String professionalID1, String professionalID2) throws IllegalArgumentException {
//
//		boolean existsSender = professionalDAO.existsByProfessionalID(senderID);
//		boolean existsReceiver = professionalDAO.existsByProfessionalID(receiverID);
//
//		if (!(existsSender && existsReceiver)) {
//
//			throw new IllegalArgumentException("Usuário não encontrado");
//		}
//
//		if (professionalID1.equalsIgnoreCase(professionalID2)) {
//
//			throw new IllegalArgumentException("Os IDs são iguais");
//		}
//
//		if (friendshipDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2)
//				|| friendshipDAO.existsByProfessionalID2AndProfessionalID1(professionalID1, professionalID2)) {
//
//			return ATIVO;
//		}
//
//		if (friendshipRequestDAO.existsByProfessionalID1AndProfessionalID2(professionalID1, professionalID2)) {
//
//			return PENDENTE_SOLICITACAO;
//		}
//
//		if (friendshipRequestDAO.existsByProfessionalID2AndProfessionalID1(professionalID1, professionalID2)) {
//
//			return PENDENTE_RESPOSTA;
//		}
//
//		return INATIVO;
//	}
//
//	public List<String> getFriendsInCommon(String professionalID1, String professionalID2) {
//
//		List<ProfessionalDomain> professional1friendList = returnFriendsList(professionalID1);
//		List<ProfessionalDomain> professional2friendList = returnFriendsList(professionalID2);
//		List<String> friendsInCommon = new ArrayList<String>();
//
//		for (ProfessionalDomain professional1 : professional1friendList) {
//
//			for (ProfessionalDomain professional2 : professional2friendList) {
//
//				if (professional1.getProfessionalID().equals(professional2.getProfessionalID())) {
//
//					friendsInCommon.add(professional1.getProfessionalID());
//				}
//			}
//		}
//
//		return friendsInCommon;
//	}
//
//	public void unfriend(String professionalID1, String professionalID2) {
//
//		boolean existsProfessional1 = professionalDAO.existsByProfessionalID(professionalID1);
//		boolean existsProfessional2 = professionalDAO.existsByProfessionalID(professionalID2);
//
//		if (!(existsProfessional1 && existsProfessional2)) {
//
//			throw new IllegalArgumentException("Usuário não encontrado");
//		}
//
//		if (!(friendshipDAO.existsByProfessionalID1AndProfessionalID2(professionalID1,
//				professionalID2)
//				|| friendshipDAO.existsByProfessionalID2AndProfessionalID1(professionalID2,
//						professionalID1))) {
//
//			throw new IllegalArgumentException("Não há amizade entre esses dois profissionais");
//		}
//
//		FriendshipDomain friendship = friendshipDAO.findByProfessionalID1AndProfessionalID2(professionalID1,
//				professionalID2);
//
//		friendshipDAO.delete(friendship);
//	}

}
