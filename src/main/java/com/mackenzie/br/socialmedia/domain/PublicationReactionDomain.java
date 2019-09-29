package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PublicationReactionCollection")
public class PublicationReactionDomain {
	
	@Id
	private String publicationReactionID;
	
	@NotNull
	private String professionalID;
	
	@NotNull
	private String publicationID;

	public String getPublicationReactionID() {
		return publicationReactionID;
	}

	public void setPublicationReactionID(String publicationReactionID) {
		this.publicationReactionID = publicationReactionID;
	}

	public String getProfessionalID() {
		return professionalID;
	}

	public void setProfessionalID(String professionalID) {
		this.professionalID = professionalID;
	}

	public String getPublicationID() {
		return publicationID;
	}

	public void setPublicaitonID(String publicaitonID) {
		this.publicationID = publicaitonID;
	}
	
}
