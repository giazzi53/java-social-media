package com.mackenzie.br.socialmedia.domain;

//import com.mackenzie.br.socialmedia.enums.FriendshipStatusEnum;

public class FriendshipDomain {
	
	private ProfessionalDomain professional1;
	
	private ProfessionalDomain professional2;
	
//	private FriendshipStatusEnum friendShipStatus;

	public FriendshipDomain() {

	}

	public ProfessionalDomain getProfessional1() {
		return professional1;
	}

	public void setProfessional1(ProfessionalDomain professional1) {
		this.professional1 = professional1;
	}

	public ProfessionalDomain getProfessional2() {
		return professional2;
	}

	public void setProfessional2(ProfessionalDomain professional2) {
		this.professional2 = professional2;
	}

//	public FriendshipStatusEnum getFriendShipStatus() {
//		return friendShipStatus;
//	}
//
//	public void setFriendShipStatus(FriendshipStatusEnum friendShipStatus) {
//		this.friendShipStatus = friendShipStatus;
	}

//}
