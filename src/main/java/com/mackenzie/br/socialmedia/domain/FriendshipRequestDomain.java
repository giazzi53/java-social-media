package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FriendshipRequestCollection")
public class FriendshipRequestDomain {
	
	@Id
	private String friendshipRequestID;
	
	@NotNull
	private String requestSenderID;
	
	@NotNull
	private String requestReceiverID;

	public String getFriendshipRequestID() {
		return friendshipRequestID;
	}

	public String getRequestSenderID() {
		return requestSenderID;
	}

	public void setRequestSenderID(String requestSenderID) {
		this.requestSenderID = requestSenderID;
	}

	public String getRequestReceiverID() {
		return requestReceiverID;
	}

	public void setRequestReceiverID(String requestReceiverID) {
		this.requestReceiverID = requestReceiverID;
	}

}
