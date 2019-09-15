package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class JobRoleDomain {
	
	@NotNull
	@Min(value=0)
	private double salary;
	
	@NotNull
	private String companyName;

	public JobRoleDomain() {

	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}

}