package com.mackenzie.br.socialmedia.enums;

import org.springframework.stereotype.Service;

@Service
public enum InstructionLevelEnum{
	
	NONE("Nenhum nivel de instrucao"),
	BACHELOR("Bacharelado"),
	MASTER("Mestrado"),
	DOCTOR("Doutorado");
	
	private String description;
	
	InstructionLevelEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
