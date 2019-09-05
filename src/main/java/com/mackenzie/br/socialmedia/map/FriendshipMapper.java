package com.mackenzie.br.socialmedia.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;

@Service
public class FriendshipMapper {

	public List<String> mapListFriendsId(String professionalID,
			List<FriendshipDomain> listFriendships1,
			List<FriendshipDomain> listFriendships2) {
		
		List<FriendshipDomain> listFriendships = new ArrayList<FriendshipDomain>();
		listFriendships.addAll(listFriendships1);
		listFriendships.addAll(listFriendships2);
		
		List<String> listFriendsID = new ArrayList<String>();
		
		for (FriendshipDomain friendship : listFriendships){
			if (friendship.getProfessionalID1().equalsIgnoreCase(professionalID)) {
				listFriendsID.add(friendship.getProfessionalID2());
			}else {
				listFriendsID.add(friendship.getProfessionalID1());
			}
		}
		return listFriendsID;
	}
	
}
