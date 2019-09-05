package com.mackenzie.br.socialmedia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mackenzie.br.socialmedia.enums.FriendshipStatusEnum;

@Document(collection = "FriendshipCollection")
public class FriendshipDomain {
	
	@Id
	private String friendshipId;
	
	private String professionalID1;
	
	private String professionalID2;
	
	private FriendshipStatusEnum friendShipStatus;

	public FriendshipDomain() {
	}

	public FriendshipStatusEnum getFriendShipStatus() {
		return friendShipStatus;
	}

	public void setFriendShipStatus(FriendshipStatusEnum friendShipStatus) {
		this.friendShipStatus = friendShipStatus;
	}

	public String getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(String friendshipId) {
		this.friendshipId = friendshipId;
	}

	public String getProfessionalID1() {
		return professionalID1;
	}

	public void setProfessionalID1(String professionalID1) {
		this.professionalID1 = professionalID1;
	}

	public String getProfessionalID2() {
		return professionalID2;
	}

	public void setProfessionalID2(String professionalID2) {
		this.professionalID2 = professionalID2;
	}
	
}
