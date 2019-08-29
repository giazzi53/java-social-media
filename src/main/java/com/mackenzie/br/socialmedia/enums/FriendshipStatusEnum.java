package com.mackenzie.br.socialmedia.enums;

import org.springframework.stereotype.Service;

@Service
public enum FriendshipStatusEnum{
	
	ACTIVE("Ativo"),
	PENDING("Pendente"),
	INACTIVE("Inativo");
	
	private String description;
	
	FriendshipStatusEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
