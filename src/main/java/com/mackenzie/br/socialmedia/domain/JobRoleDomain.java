package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class JobRoleDomain {
	
	@NotNull
	private String jobTitle;
	
	@NotNull
	@Min(value=0)
	private double salary;
	
	@NotNull
	private String companyName;

	public JobRoleDomain() {

	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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