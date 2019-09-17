package com.mackenzie.br.socialmedia.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.Professional_FriendsDomain;
import com.mackenzie.br.socialmedia.service.AnalyticsService;

@RestController
public class AnalyticsController {
	
	@Autowired
	AnalyticsService analyticsService;
	
	@CrossOrigin("*")
	@GetMapping(value="/getAvgNumberOfFriends")
	public long getAvgNumberOfFriends() {
		return analyticsService.getAvgNumberOfFriends();
	}
	
	@CrossOrigin("*")
	@GetMapping(value="/getTop10ProfessionalsWithMostFriends")
	public List<Professional_FriendsDomain> getTop10ProfessionalsWithMostFriends(){
		return analyticsService.getTop10ProfessionalsWithMostFriends();
	}
	
}
