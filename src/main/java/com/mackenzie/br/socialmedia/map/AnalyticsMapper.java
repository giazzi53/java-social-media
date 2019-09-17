package com.mackenzie.br.socialmedia.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.domain.Professional_FriendsDomain;

@Service
public class AnalyticsMapper {
	
	public List<Professional_FriendsDomain> mapListToDescendingOrder(List<Professional_FriendsDomain> listOfProfessionalFriends) {
		Collections.sort(listOfProfessionalFriends, new Comparator<Professional_FriendsDomain>() {
			  public int compare(Professional_FriendsDomain professionalFriends1, Professional_FriendsDomain professionalFriends2) {
			      return professionalFriends1.getNumberOfFriends().compareTo(professionalFriends2.getNumberOfFriends());
			      
			  }
			});
		Collections.reverse(listOfProfessionalFriends);
		
		List<Professional_FriendsDomain> listOfTop10ProfessionalFriends = new ArrayList<Professional_FriendsDomain>();
		int i = 0;
		while(i<10) {
			listOfTop10ProfessionalFriends.add(listOfProfessionalFriends.get(i));
			i+=1;
		}
		return listOfTop10ProfessionalFriends;
	}
}
