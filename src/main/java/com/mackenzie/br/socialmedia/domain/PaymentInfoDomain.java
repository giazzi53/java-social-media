package com.mackenzie.br.socialmedia.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "PaymentCollection")
public class PaymentInfoDomain {
	
	@Id
	private String paymentInfoID;
	
	@JsonIgnore
	private String professionalID;
	
	private String cardNumber;
	
	private String cardName;
	
	private Date cardValidationDate;

	private String cardSecurityCode;
	
	public String getProfessionalID() {
		return professionalID;
	}
	
	public void setProfessionalID(String professionalID) {
		this.professionalID = professionalID;
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

	public String getCardSecurityCode() {
		return cardSecurityCode;
	}

	public void setCardSecurityCode(String cardSecurityCode) {
		this.cardSecurityCode = cardSecurityCode;
	}
	
}
