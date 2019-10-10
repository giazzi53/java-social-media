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
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

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
	private	ValidationUtils validationUtils;	

	private static final int ACTIVE = 1;

	private static final int PENDING_REQUEST = 2;

	private static final int PENDING_RESPONSE = 3;

	private static final int INACTIVE = 4;

	public FriendshipRequestDomain sendFriendshipRequest(FriendshipSenderReceiverDomain senderReceiver) throws IllegalArgumentException {

		String senderID = senderReceiver.getSenderID();
		String receiverID = senderReceiver.getReceiverID();
		
		validationUtils.validateProfessionalByID(professionalDAO, senderID);
		validationUtils.validateProfessionalByID(professionalDAO, receiverID);

		List<String> friendsIDsList = friendshipMapper.mapFriendsIDsList(senderID,
				friendshipDAO.findByProfessionalID1(senderID),
				friendshipDAO.findByProfessionalID2(senderID));

		for (String friendID : friendsIDsList) {

			if (friendID.equalsIgnoreCase(senderID)) {

				throw new IllegalArgumentException("Este usuário já é seu amigo");
			}
		}
		
		validationUtils.validateProfessionalsByEqualIDs(senderID, receiverID);

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

		validationUtils.validateProfessionalByID(professionalDAO, senderID);
		validationUtils.validateProfessionalByID(professionalDAO, receiverID);

		validationUtils.validateProfessionalsByEqualIDs(senderID, receiverID);

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

		validationUtils.validateProfessionalByID(professionalDAO, senderID);
		validationUtils.validateProfessionalByID(professionalDAO, receiverID);

		validationUtils.validateProfessionalsByEqualIDs(senderID, receiverID);

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

		throw new IllegalArgumentException("Solicitação não encontrada");
	}

	public List<ProfessionalDomain> returnFriendsList(String professionalID) throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, professionalID);
		
		List<String> listFriendsID = friendshipMapper.mapFriendsIDsList(professionalID,
				friendshipDAO.findByProfessionalID1(professionalID),
				friendshipDAO.findByProfessionalID2(professionalID));

		List<ProfessionalDomain> friendsList = new ArrayList<ProfessionalDomain>();

		for (String id : listFriendsID) {

			friendsList.add(professionalDAO.findByProfessionalID(id));
		}

		return friendsList;
	}

	public int getFriendshipStatus(String senderID, String receiverID) throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, senderID);
		validationUtils.validateProfessionalByID(professionalDAO, receiverID);

		validationUtils.validateProfessionalsByEqualIDs(senderID, receiverID);

		if (friendshipDAO.existsByProfessionalID1AndProfessionalID2(senderID, receiverID)
				|| friendshipDAO.existsByProfessionalID2AndProfessionalID1(senderID, receiverID)) {

			return ACTIVE;
		}

		if (friendshipRequestDAO.existsByRequestSenderIDAndRequestReceiverID(senderID, receiverID)) {

			return PENDING_REQUEST;
		}

		if (friendshipRequestDAO.existsByRequestReceiverIDAndRequestSenderID(receiverID, senderID)) {

			return PENDING_RESPONSE;
		}

		return INACTIVE;
	}

	public List<String> getFriendsInCommon(String senderID, String receiverID) {

		List<ProfessionalDomain> professional1friendList = returnFriendsList(senderID);
		List<ProfessionalDomain> professional2friendList = returnFriendsList(receiverID);
		List<String> friendsInCommon = new ArrayList<String>();

		for (ProfessionalDomain professional1 : professional1friendList) {

			for (ProfessionalDomain professional2 : professional2friendList) {

				if (professional1.getProfessionalID().equals(professional2.getProfessionalID())) {

					friendsInCommon.add(professional1.getProfessionalID());
				}
			}
		}

		return friendsInCommon;
	}

	public void unfriend(String senderID, String receiverID) {

		validationUtils.validateProfessionalByID(professionalDAO, senderID);
		validationUtils.validateProfessionalByID(professionalDAO, receiverID);

		if (!(friendshipDAO.existsByProfessionalID1AndProfessionalID2(senderID,
				receiverID)
				|| friendshipDAO.existsByProfessionalID2AndProfessionalID1(receiverID,
						senderID))) {

			throw new IllegalArgumentException("Não há amizade entre esses dois profissionais");
		}

		FriendshipDomain friendship = friendshipDAO.findByProfessionalID1AndProfessionalID2(senderID,
				receiverID);

		friendshipDAO.delete(friendship);
	}

}
