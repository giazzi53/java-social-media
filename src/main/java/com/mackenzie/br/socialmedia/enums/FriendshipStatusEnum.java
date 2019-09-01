package com.mackenzie.br.socialmedia.enums;

public enum FriendshipStatusEnum {
	
	PENDING("Pendente"),
	ACTIVE("Ativo"),
	INACTIVE("Inativo");
	
	private String description;
	
	FriendshipStatusEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
