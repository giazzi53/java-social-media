package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class AccessService {
	
	public AccessService() {
		super();
	}

	@Autowired
	private ProfessionalDAO professionalDAO;

	public ProfessionalDomain signUp(ProfessionalDomain professional) {
		ProfessionalDomain databaseProfessional = professionalDAO.insert(professional);	
		
		return databaseProfessional;
	}

	public ProfessionalDomain login(ProfessionalDomain professional) throws IllegalAccessException {
		
		List<ProfessionalDomain> databaseProfessional = professionalDAO.findByUserLoginAndPassword(professional.getUserLogin(), professional.getPassword());
		//List<ProfessionalDomain> passwordProfessional = professionalDAO.findByPassword(professional.getPassword());
	
		if(databaseProfessional.size() != 1) { 
			// login query list must return only one object
			// password query list must return one or more objects (same password can be used for different users)
			
			throw new IllegalAccessException("Usuário ou senha incorretos");
		}
		
		return databaseProfessional.get(0);
	}

	public ProfessionalDomain updateProfile(ProfessionalDomain professional) {
		List<ProfessionalDomain> profissional = professionalDAO.findByUserLogin(professional.getUserLogin());
		profissional.get(0).setName(professional.getName());
		professionalDAO.save(profissional.get(0));

		return profissional.get(0);
	}

}
