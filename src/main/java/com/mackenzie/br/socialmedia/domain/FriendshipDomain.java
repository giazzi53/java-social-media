package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FriendshipCollection")
public class FriendshipDomain {
	
	@Id
	private String friendshipId;
	
	@NotNull
	private String professionalID1;
	
	@NotNull
	private String professionalID2;

	public FriendshipDomain() {
	}

	public String getFriendshipId() {
		return friendshipId;
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
