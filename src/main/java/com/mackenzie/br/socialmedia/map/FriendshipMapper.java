package com.mackenzie.br.socialmedia.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;

@Service
public class FriendshipMapper {
	
	@Autowired
	private FriendshipDAO friendshipDAO;
	
	public List<String> mapFriendsIDsList(String professionalID) {
		
		List<FriendshipDomain> listFriendships = new ArrayList<FriendshipDomain>();
		listFriendships.addAll(friendshipDAO.findAllByProfessionalID1(professionalID));
		listFriendships.addAll(friendshipDAO.findAllByProfessionalID2(professionalID));
		
		List<String> listFriendsID = new ArrayList<String>();
		
		for (FriendshipDomain friendship : listFriendships){
			
			if (friendship.getProfessionalID1().equalsIgnoreCase(professionalID)) {
				
				listFriendsID.add(friendship.getProfessionalID2());
			} else {
				
				listFriendsID.add(friendship.getProfessionalID1());
			}
			
		}
		
		return listFriendsID;
	}
	
}
