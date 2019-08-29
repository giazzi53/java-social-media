package com.mackenzie.br.socialmedia.enums;

import org.springframework.stereotype.Service;

@Service
public enum ProfileTypeEnum{
	
	STANDARD("Standard"),
	PREMIUM("Premium"),
	MANAGER("Gerente");
	
	private String description;
	
	ProfileTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
