package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.InterestTopicDAO;
import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;

@Service
public class InterestTopicService {
	
	@Autowired
	private InterestTopicDAO interestTopicDAO;
	
	public List<InterestTopicDomain> getInterestTopics() {
		
		return interestTopicDAO.findAll();
	}

}
