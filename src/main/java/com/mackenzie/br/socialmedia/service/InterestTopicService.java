package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mackenzie.br.socialmedia.DAO.InterestTopicDAO;
import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;

public class InterestTopicService {
	
	@Autowired
	InterestTopicDAO interestTopicDAO;
	
	public List<InterestTopicDomain> getInterestTopics() {
		return interestTopicDAO.findAll();
	}

}
