package com.mackenzie.br.socialmedia.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PublicationCollection")
public class PublicationDomain {
	
	@Id	
	private String publicationID;
	
	private String author;
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthorID(String author) {
		this.author = author;
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
