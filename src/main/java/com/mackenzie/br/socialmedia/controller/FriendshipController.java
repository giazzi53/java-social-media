package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.service.FriendshipService;

@RestController
public class FriendshipController {
	
	@Autowired
	private FriendshipService friendshipService;

	@PostMapping(value = "/sendFriendshipRequest")
	public ResponseEntity<FriendshipRequestDomain> sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipRequestDomain friendshipRequest;
		try {
			friendshipRequest = friendshipService.sendFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}
	
	@PostMapping(value = "/acceptFriendshipRequest")
	public ResponseEntity<FriendshipDomain> acceptFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipDomain friendship;
		try {
			friendship = friendshipService.acceptFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(friendship, HttpStatus.OK);
	}
	
	@PostMapping(value = "/rejectFriendshipRequest")
	public ResponseEntity<FriendshipRequestDomain> rejectFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipRequestDomain friendshipRequest;
		
		try {
			friendshipRequest = friendshipService.rejectFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}

	@PostMapping(value = "/returnListFriends")
	public ResponseEntity<List<ProfessionalDomain>> returnListFriends(@RequestBody ProfessionalDomain professional) {
		List<ProfessionalDomain> list;
		try {
			list = friendshipService.returnListFriends(professional);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
