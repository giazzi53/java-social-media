package com.mackenzie.br.socialmedia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Professional_InterestTopicCollection")
public class Professional_InterestTopicDomain {
	
	@Id
	private String professional_InterestTopicID;
	
	private String professionalID;
	
	private String interestTopicID;
	
	public Professional_InterestTopicDomain() {
		
	}

	public String getProfessional_InterestTopicID() {
		return professional_InterestTopicID;
	}

	public String getProfessionalID() {
		return professionalID;
	}

	public String getInterestTopicID() {
		return interestTopicID;
	}

}
