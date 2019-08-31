package com.mackenzie.br.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@RestController
public class FriendshipController {
	
	@Autowired
	private FriendshipDAO friendshipDAO;

	@RequestMapping(value = "/sendFriendshipRequest", method = RequestMethod.POST)
	public String sendFriendshipRequest(@RequestBody FriendshipDomain friendshipDomain) {
		
		
		
		return "Solicitação de amizade enviada";
	}
	
	@RequestMapping(value = "/acceptFriendshipRequest", method = RequestMethod.POST)
	public String acceptFriendshipRequest(@RequestBody FriendshipDomain FriendshipDomain) {
		
		
		return "Solicitação de amizade aceita";
	}
	
	@RequestMapping(value = "/rejectFriendshipRequest", method = RequestMethod.POST)
	public String rejectFriendshipRequest(@RequestBody FriendshipDomain FriendshipDomain) {
		
		
		return "Solicitação de amizade recusada";
	}
}
