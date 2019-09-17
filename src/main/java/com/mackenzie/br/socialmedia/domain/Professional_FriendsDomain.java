package com.mackenzie.br.socialmedia.domain;

public class Professional_FriendsDomain {
	
	private ProfessionalDomain professional;
	
	private Long numberOfFriends;

	public ProfessionalDomain getProfessional() {
		return professional;
	}

	public void setProfessional(ProfessionalDomain professional) {
		this.professional = professional;
	}

	public Long getNumberOfFriends() {
		return numberOfFriends;
	}

	public void setNumberOfFriends(Long numberOfFriends) {
		this.numberOfFriends = numberOfFriends;
	} 
}
