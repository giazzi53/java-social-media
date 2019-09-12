package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;
import com.mackenzie.br.socialmedia.service.InterestTopicService;

@RestController
public class InterestTopicController {
	
	@Autowired
	InterestTopicService interestTopicService;
	
	@GetMapping(value = "/getInterestTopics")
	public List<InterestTopicDomain> getInterestTopics(){
		return interestTopicService.getInterestTopics();
	}
	
}
