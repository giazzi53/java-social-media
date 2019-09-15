package com.mackenzie.br.socialmedia.enums;

public enum FriendshipStatusEnum {
	
	PENDENTE("Pendente"),
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String description;
	
	FriendshipStatusEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
