package com.mackenzie.br.socialmedia.domain;

public class FriendshipSenderReceiverDomain {
	
	private String senderID;
	
	private String receiverID;

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

}
