package com.mackenzie.br.socialmedia.domain;

import java.util.Comparator;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PublicationCollection")
public class PublicationDomain implements Comparator<PublicationDomain>{
	
	@Id
	private String publicationID;
	
	@NotNull
	private String professionalID;
	
	@NotNull
	private String author;
	
	@NotNull
	private String text;
	
	private Date publicationDate;

	public PublicationDomain() {

	}

	public String getPublicationID() {
		return publicationID;
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

	public String getProfessionalID() {
		return professionalID;
	}

	public void setProfessionalID(String professionalID) {
		this.professionalID = professionalID;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int compare(PublicationDomain o1, PublicationDomain o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
