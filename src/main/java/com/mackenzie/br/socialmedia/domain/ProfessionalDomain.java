package com.mackenzie.br.socialmedia.domain;

import java.io.File;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@Document(collection = "ProfessionalCollection")
public class ProfessionalDomain {

	@Id
	private String professionalID;
	
	@NotNull
	private String name;
	
	@NotNull
	private String userLogin;

	//@JsonIgnore
	@NotNull
	private String password;

	private File profileImage;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@DateTimeFormat
	private Date birthDate;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@DateTimeFormat
	private Date careerDate;
	
	//@JsonProperty
	@NotNull
	@Valid
	private JobRoleDomain jobRole;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	private PaymentDomain payment;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	private InstructionLevelEnum instructionLevel;
	
	private ProfileTypeEnum profileType;

	public ProfessionalDomain() {
		
	}

	public JobRoleDomain getJobRole() {
		return jobRole;
	}

	public InstructionLevelEnum getInstructionLevel() {
		return instructionLevel;
	}

	public void setInstructionLevel(InstructionLevelEnum instructionLevel) {
		this.instructionLevel = instructionLevel;
	}

	public ProfileTypeEnum getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileTypeEnum profileType) {
		this.profileType = profileType;
	}

	public String getProfessionalID() {
		return professionalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public File getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCareerDate() {
		return careerDate;
	}

	public void setCareerDate(Date careerDate) {
		this.careerDate = careerDate;
	}

	public void setJobRole(JobRoleDomain jobRole) {
		this.jobRole = jobRole;
	}

	public PaymentDomain getPayment() {
		return payment;
	}

	public void setPayment(PaymentDomain payment) {
		this.payment = payment;
	}
	
}