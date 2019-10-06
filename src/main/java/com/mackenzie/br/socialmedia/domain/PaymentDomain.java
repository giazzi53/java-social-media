package com.mackenzie.br.socialmedia.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PaymentCollection")
public class PaymentDomain {
	
	private String professionalID;
	
	private String cardNumber;
	
	private String cardName;
	
	private Date cardValidationDate;

	private String cardCode;
	
	public String getProfessionalID() {
		return professionalID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Date getCardValidationDate() {
		return cardValidationDate;
	}

	public void setCardValidationDate(Date cardValidationDate) {
		this.cardValidationDate = cardValidationDate;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	
}
