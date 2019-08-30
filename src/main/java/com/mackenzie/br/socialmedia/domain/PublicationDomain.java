package com.mackenzie.br.socialmedia.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class PublicationDomain {
	
	private String publicationID;
	
	private String authorID;
	
	private String text;
	
	private Date publicationDate;

	public PublicationDomain() {

	}

	public String getPublicationID() {
		return publicationID;
	}

	public void setPublicationID(String publicationID) {
		this.publicationID = publicationID;
	}

	public String getAuthorID() {
		return authorID;
	}

	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
