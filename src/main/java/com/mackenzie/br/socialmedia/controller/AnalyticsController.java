package com.mackenzie.br.socialmedia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.Professional_FriendsDomain;
import com.mackenzie.br.socialmedia.service.AnalyticsService;

@RestController
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@CrossOrigin(value = "*")
	@GetMapping(value="/getAvgNumberOfFriends")
	public BigDecimal getAvgNumberOfFriends() {
		
		return analyticsService.getAvgNumberOfFriends();
	}
	
	@CrossOrigin(value = "*")
	@GetMapping(value="/getTop10ProfessionalsWithMostFriends")
	public List<Professional_FriendsDomain> getTop10ProfessionalsWithMostFriends(){
		
		return analyticsService.getTop10ProfessionalsWithMostFriends();
	}
	
	@CrossOrigin(value = "*")
	@GetMapping(value="/getNumberOfProfessionals")
	public BigDecimal getNumberOfProfessionals(){
		
		return analyticsService.getNumberOfProfessionals();
	}
	
}
