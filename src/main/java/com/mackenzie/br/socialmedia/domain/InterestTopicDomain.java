package com.mackenzie.br.socialmedia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "InterestTopicCollection")
public class InterestTopicDomain {
	
	@Id
	private String interestTopicID;
	
	private String description;
	
	public InterestTopicDomain() {
		
	}

	public String getInterestTopicID() {
		return interestTopicID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
