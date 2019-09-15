package com.mackenzie.br.socialmedia.enums;

public enum InstructionLevelEnum {
	
	NENHUM("Nenhum nível de instrução"),
	BACHARELADO("Bacharelado"),
	MESTRADO("Mestrado"),
	DOUTOR("Doutorado");
	
	private String description;
	
	InstructionLevelEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
