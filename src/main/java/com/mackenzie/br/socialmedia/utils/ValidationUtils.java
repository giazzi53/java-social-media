package com.mackenzie.br.socialmedia.utils;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;

public class ValidationUtils {

	public boolean validateProfessionalByID(ProfessionalDAO professionalDAO, String professionalID) {

		boolean existsProfessional = professionalDAO.existsByProfessionalID(professionalID);

		if (!existsProfessional) {
			
			throw new IllegalArgumentException("Usuário não encontrado");
		}

		return true;
	}

	public boolean validateProfessionalByUserLoginAndPassword(ProfessionalDAO professionalDAO, String userLogin, String password) throws IllegalAccessException {
			
		boolean existsProfessional = professionalDAO.existsByUserLoginAndPassword(userLogin, password);
	
		if(!existsProfessional) { 
			
			throw new IllegalAccessException("Usuário ou senha incorretos");
		}
		
		return true;
	}

	public void validateProfessionalsByEqualIDs(String professionalID1, String professionalID2) {
		
		if (professionalID1.equalsIgnoreCase(professionalID2)) {

			throw new IllegalArgumentException("Os IDs dos profissionais são iguais");
		}
		
	}

}
