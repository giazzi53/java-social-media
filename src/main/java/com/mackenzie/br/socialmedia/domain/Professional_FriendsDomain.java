package com.mackenzie.br.socialmedia.domain;

public class Professional_FriendsDomain {
	
	private ProfessionalDomain professional;
	
	private Integer numberOfFriends;

	public ProfessionalDomain getProfessional() {
		return professional;
	}

	public void setProfessional(ProfessionalDomain professional) {
		this.professional = professional;
	}

	public Integer getNumberOfFriends() {
		return numberOfFriends;
	}

	public void setNumberOfFriends(Integer numberOfFriends) {
		this.numberOfFriends = numberOfFriends;
	} 
}
