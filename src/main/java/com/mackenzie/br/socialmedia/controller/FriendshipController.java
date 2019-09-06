package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.service.FriendshipService;

@RestController
public class FriendshipController {
	
	@Autowired
	private FriendshipService friendshipService;

	@PostMapping(value = "/sendFriendshipRequest")
	public ResponseEntity<ProfessionalDomain> sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
	//public ResponseEntity<FriendshipDomain> sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		//FriendshipDomain databaseFriendship = friendshipService.sendFriendshipRequest(professionals);
		friendshipService.sendFriendshipRequest(professionals);
		
		return new ResponseEntity<>(professionals.get(0), HttpStatus.OK);
		//return new ResponseEntity<>(databaseFriendship, HttpStatus.OK);
	}
	
	@PostMapping(value = "/acceptFriendshipRequest")
	public ResponseEntity<ProfessionalDomain> acceptFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		friendshipService.acceptFriendshipRequest(professionals);
		
		return new ResponseEntity<>(professionals.get(0), HttpStatus.OK);
	}
	
	@PostMapping(value = "/rejectFriendshipRequest")
	public ResponseEntity<ProfessionalDomain> rejectFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		friendshipService.rejectFriendshipRequest(professionals);
		
		return new ResponseEntity<>(professionals.get(0), HttpStatus.OK);
	}
	
	@PostMapping(value = "/returnListFriends")
	public ResponseEntity<List<ProfessionalDomain>> returnListFriends(@RequestBody ProfessionalDomain professional) {
		List<ProfessionalDomain> list = friendshipService.returnListFriends(professional);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
