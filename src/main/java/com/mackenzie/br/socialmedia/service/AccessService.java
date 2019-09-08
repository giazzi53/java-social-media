package com.mackenzie.br.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class AccessService {
	
	@Autowired
	private ProfessionalDAO professionalDAO;

	public ProfessionalDomain signUp(ProfessionalDomain professional) {
//		if (validateUser(professional.getUserLogin())) {
//			throw new IllegalAccessException("Usuário já em uso");
//		}
		ProfessionalDomain databaseProfessional = professionalDAO.insert(professional);	
		
		return databaseProfessional;
	}

	public ProfessionalDomain login(ProfessionalDomain professional) throws IllegalAccessException {
		boolean existsProfessional = professionalDAO.existsByUserLoginAndPassword(professional.getUserLogin(), professional.getPassword());

		if(!existsProfessional) { 
			throw new IllegalAccessException("Usuário ou senha incorretos");
		}
		
		ProfessionalDomain databaseProfessional = professionalDAO.findByUserLoginAndPassword(professional.getUserLogin(), professional.getPassword());
		
		return databaseProfessional;
	}

	public ProfessionalDomain updateProfile(ProfessionalDomain professional) throws IllegalArgumentException{
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professional.getProfessionalID());

		if(!existsProfessional) {
			throw new IllegalArgumentException("Usuário não encontrado");
		}
		
		ProfessionalDomain databaseProfessional = professionalDAO.findByProfessionalID(professional.getProfessionalID());

		databaseProfessional.setName(professional.getName());
		professionalDAO.save(databaseProfessional);

		return databaseProfessional;
	}

	public ProfessionalDomain retrieveProfessionalData(ProfessionalDomain professional) throws IllegalArgumentException{
		
		boolean existsProfessional = professionalDAO.existsByProfessionalID(professional.getProfessionalID());

		if(!existsProfessional) { 
			throw new IllegalArgumentException("Usuário ou senha incorretos");
		}
		
		return professionalDAO.findByProfessionalID(professional.getProfessionalID());
	}

	public boolean validateUser(String userLogin) {
		for (ProfessionalDomain professional : professionalDAO.findAll()){
			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
				return true;
			}
		}
		return false;
	}
	
}
