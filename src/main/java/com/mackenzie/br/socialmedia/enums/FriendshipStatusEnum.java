package com.mackenzie.br.socialmedia.enums;

public enum FriendshipStatusEnum {
	
	PENDENTE_SOLICITACAO("Pendente Solicitação"),
	PENDENTE_RESPOSTA("Pendente Resposta"),
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
