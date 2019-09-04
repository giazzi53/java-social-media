package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.service.FriendshipService;

@RestController
public class FriendshipController {
	
	@Autowired
	FriendshipService friendshipService;

	@RequestMapping(value = "/sendFriendshipRequest", method = RequestMethod.POST)
	public String sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		return friendshipService.sendFriendshipRequest(professionals);
	}
	
	@RequestMapping(value = "/acceptFriendshipRequest", method = RequestMethod.POST)
	public String acceptFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		return friendshipService.acceptFriendshipRequest(professionals);
	}
	
	@RequestMapping(value = "/rejectFriendshipRequest", method = RequestMethod.POST)
	public String rejectFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		return friendshipService.rejectFriendshipRequest(professionals);
	}

}
