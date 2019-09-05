package com.mackenzie.br.socialmedia.domain;

public class JobRoleDomain {
	
	private String jobRoleID;

	private double salary;
	
	private String companyName;

	public JobRoleDomain() {

	}

	public String getJobRoleID() {
		return jobRoleID;
	}

	public void setJobRoleID(String jobRoleID) {
		this.jobRoleID = jobRoleID;
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
