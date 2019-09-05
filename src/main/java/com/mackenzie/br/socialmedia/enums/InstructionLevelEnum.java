package com.mackenzie.br.socialmedia.enums;

public enum InstructionLevelEnum {
	
	NONE("Nenhum nível de instrução"),
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
