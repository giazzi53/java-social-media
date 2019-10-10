package com.mackenzie.br.socialmedia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipSenderReceiverDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.service.FriendshipService;

@RestController
public class FriendshipController {
	
	@Autowired
	private FriendshipService friendshipService;
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/sendFriendshipRequest")
	public ResponseEntity<?> sendFriendshipRequest(@RequestBody FriendshipSenderReceiverDomain senderReceiver) {
		
		FriendshipRequestDomain friendshipRequest;
		
		try {
			friendshipRequest = friendshipService.sendFriendshipRequest(senderReceiver);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/acceptFriendshipRequest")
	public ResponseEntity<?> acceptFriendshipRequest(@RequestBody FriendshipSenderReceiverDomain senderReceiver) {
		
		FriendshipDomain friendship;
		
		try {
			friendship = friendshipService.acceptFriendshipRequest(senderReceiver);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(friendship, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/rejectFriendshipRequest")
	public ResponseEntity<?> rejectFriendshipRequest(@RequestBody FriendshipSenderReceiverDomain senderReceiver) {
		
		FriendshipRequestDomain friendshipRequest;
		
		try {
			friendshipRequest = friendshipService.rejectFriendshipRequest(senderReceiver);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/returnFriendsList/{professionalID}")
	public ResponseEntity<?> returnFriendsList(@PathVariable String professionalID) {
		
		List<ProfessionalDomain> friendsList;
		
		try {
			friendsList = friendshipService.returnFriendsList(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(friendsList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getFriendshipStatus/{professionalID1}/{professionalID2}")
	public ResponseEntity<?> getFriendshipStatus(@PathVariable String professionalID1, @PathVariable String professionalID2){
		
		int friendshipStatus;
		
		try {
			friendshipStatus = friendshipService.getFriendshipStatus(professionalID1, professionalID2);
		}catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(friendshipStatus, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@GetMapping("/getFriendsInCommon/{professionalID1}/{professionalID2}")
	public ResponseEntity<?> getFriendsInCommon(@PathVariable String professionalID1, @PathVariable String professionalID2){
		
		List<String> friendsInCommonList = new ArrayList<String>();
		
		try {
			friendsInCommonList = friendshipService.getFriendsInCommon(professionalID1, professionalID2);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(friendsInCommonList, HttpStatus.OK);
	}
	
	@CrossOrigin("*")
	@DeleteMapping("/unfriend/{professionalID1}/{professionalID2}")
	public ResponseEntity<?> unfriend(@PathVariable String professionalID1, @PathVariable String professionalID2){
		
		try {
			friendshipService.unfriend(professionalID1, professionalID2);
		} catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}