package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.NotNull;

public class JobRoleDomain {
	
	private double salary;
	
	@NotNull
	private String companyName;

	public JobRoleDomain() {

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
