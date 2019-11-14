package com.mackenzie.br.socialmedia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.Professional_FriendsDomain;
import com.mackenzie.br.socialmedia.map.AnalyticsMapper;

@Service
public class AnalyticsService {
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private FriendshipDAO friendshipDAO;
	
	@Autowired
	private AnalyticsMapper analyticsMapper;

	public double getAvgNumberOfFriends() {
		
		double avgNumberOfFriends = (friendshipDAO.count() * 2) / professionalDAO.count();
				
		
		return avgNumberOfFriends;
	}
	
	public List<Professional_FriendsDomain> getTop10ProfessionalsWithMostFriends(){
		
		List<Professional_FriendsDomain> professionalFriendsList = new ArrayList<Professional_FriendsDomain>();
		
		for (ProfessionalDomain professional : professionalDAO.findAll()) {
			
			Professional_FriendsDomain professionalFriends = new Professional_FriendsDomain();
			professionalFriends.setProfessional(professional);
			professionalFriends.setNumberOfFriends(friendshipDAO.countByProfessionalID1OrProfessionalID2(professional.getProfessionalID(), professional.getProfessionalID()));
			professionalFriendsList.add(professionalFriends);
		}
		
		return analyticsMapper.mapListToDescendingOrder(professionalFriendsList);
	}

	public BigDecimal getNumberOfProfessionals() {
		
		return new BigDecimal(professionalDAO.count());
	}
}
