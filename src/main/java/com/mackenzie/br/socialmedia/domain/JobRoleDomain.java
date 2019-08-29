package com.mackenzie.br.socialmedia.domain;

public class JobRoleDomain {
	
	private String jobRoleID;
	
	private String professionalID;
	
	private double salary;
	
	private String companyName;

	public JobRoleDomain(String jobRoleID, String professionalID, double salary, String companyName) {
		this.jobRoleID = jobRoleID;
		this.professionalID = professionalID;
		this.salary = salary;
		this.companyName = companyName;
	}

	public String getJobRoleID() {
		return jobRoleID;
	}

	public void setJobRoleID(String jobRoleID) {
		this.jobRoleID = jobRoleID;
	}

	public String getProfessionalID() {
		return professionalID;
	}

	public void setProfessionalID(String professionalID) {
		this.professionalID = professionalID;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
