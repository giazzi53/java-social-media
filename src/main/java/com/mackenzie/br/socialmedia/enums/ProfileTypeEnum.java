package com.mackenzie.br.socialmedia.enums;

public enum ProfileTypeEnum {
	
	STANDARD("Standard"),
	PREMIUM("Premium"),
	GERENTE("Gerente");
	
	private String description;
	
	ProfileTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
