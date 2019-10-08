package com.mackenzie.br.socialmedia.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;

@Service
public class FriendshipMapper {

	public List<String> mapFriendsIDsList(String professionalID, FriendshipDomain friendship1,
			FriendshipDomain friendship2) {

		List<FriendshipDomain> friendshipsList = new ArrayList<FriendshipDomain>();
		
		friendshipsList.add(friendship1);
		friendshipsList.add(friendship2);

		List<String> friendsIDsList = new ArrayList<String>();

		for (FriendshipDomain friendship : friendshipsList) {
			
			if (friendship.getProfessionalID1().equalsIgnoreCase(professionalID)) {
				friendsIDsList.add(friendship.getProfessionalID2());
			} else {
				friendsIDsList.add(friendship.getProfessionalID1());
			}
		}
		
		return friendsIDsList;
	}

}
