package com.mackenzie.br.socialmedia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
<<<<<<< HEAD
	@CrossOrigin(origins = "*")
=======
	@CrossOrigin(value = "*")
>>>>>>> 87424efe9548780a676413fc76dc2d2aa6afb54c
	@PostMapping(value = "/sendFriendshipRequest")
	public ResponseEntity<?> sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipRequestDomain friendshipRequest;
		
		try {
			friendshipRequest = friendshipService.sendFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}
	
<<<<<<< HEAD
	@CrossOrigin(origins = "*")
=======
	@CrossOrigin(value = "*")
>>>>>>> 87424efe9548780a676413fc76dc2d2aa6afb54c
	@PostMapping(value = "/acceptFriendshipRequest")
	public ResponseEntity<?> acceptFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipDomain friendship;
		
		try {
			friendship = friendshipService.acceptFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(friendship, HttpStatus.OK);
	}
	
<<<<<<< HEAD
	@CrossOrigin(origins = "*")
=======
	@CrossOrigin(value = "*")
>>>>>>> 87424efe9548780a676413fc76dc2d2aa6afb54c
	@PostMapping(value = "/rejectFriendshipRequest")
	public ResponseEntity<?> rejectFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		FriendshipRequestDomain friendshipRequest;
		
		try {
			friendshipRequest = friendshipService.rejectFriendshipRequest(professionals);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(friendshipRequest, HttpStatus.OK);
	}
	
<<<<<<< HEAD
	@CrossOrigin(origins = "*")
=======
	@CrossOrigin(value = "*")
>>>>>>> 87424efe9548780a676413fc76dc2d2aa6afb54c
	@GetMapping(value = "/returnListFriends/{professionalID}")
	public ResponseEntity<?> returnListFriends(@PathVariable String professionalID) {
		List<ProfessionalDomain> list;
		
		try {
			list = friendshipService.returnListFriends(professionalID);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
<<<<<<< HEAD
	@CrossOrigin(origins = "*")
=======
	@CrossOrigin(value = "*")
>>>>>>> 87424efe9548780a676413fc76dc2d2aa6afb54c
	@PostMapping(value = "/getStatusFriendship")
	public ResponseEntity<?> getStatusFriendship(@RequestBody List<ProfessionalDomain> listOfProfessionals){
		Integer status;
		
		try {
			status = friendshipService.getStatusFriendship(listOfProfessionals);
		}catch(IllegalArgumentException i) {
			return new ResponseEntity<>(i.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@CrossOrigin(value = "*")
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

}
