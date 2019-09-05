package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.FriendshipRequestDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.FriendshipRequestDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class AccessService {
	
	public AccessService() {
		super();
	}

	@Autowired
	ProfessionalDAO professionalDAO;

	public ResponseEntity<ProfessionalDomain> signUp(ProfessionalDomain professional) {
		professionalDAO.insert(professional);
		return new ResponseEntity<>(professional, HttpStatus.CREATED);
		
	}

	public String login(ProfessionalDomain professional) {
		
		List<ProfessionalDomain> userLoginProfessional = professionalDAO.findByUserLogin(professional.getUserLogin());
		List<ProfessionalDomain> passwordProfessional = professionalDAO.findByPassword(professional.getPassword());
	
		if((!(userLoginProfessional.size() == 1)) || passwordProfessional.isEmpty()) { 
			// login query list must return only one object
			// password query list must return one or more objects (same password can be used for different users)
			return "Usuário ou senha incorretos";
		}
		
		return "Bem vindo, " + userLoginProfessional.get(0).getName();
	}

	public ResponseEntity<ProfessionalDomain> updateProfile(ProfessionalDomain professional) {
		
		List<ProfessionalDomain> profissional = professionalDAO.findByUserLogin(professional.getUserLogin());
		profissional.get(0).setName(professional.getName());
		professionalDAO.save(profissional.get(0));

		return new ResponseEntity<>(profissional.get(0),HttpStatus.OK);
	}

}
