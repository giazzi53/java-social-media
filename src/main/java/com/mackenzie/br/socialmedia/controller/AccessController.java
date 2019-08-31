package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
//import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
//import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@RestController
public class AccessController {

	@Autowired
	ProfessionalDAO professionalDAO;

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@RequestBody ProfessionalDomain professional) {

		professionalDAO.insert(professional);
				 
		return "Cadastro realizado com sucesso";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestBody ProfessionalDomain professional) {
		
		List<ProfessionalDomain> userLoginProfessional = professionalDAO.findByUserLogin(professional.getUserLogin());
		List<ProfessionalDomain> passwordProfessional = professionalDAO.findByPassword(professional.getPassword());
	
		if((!(userLoginProfessional.size() == 1)) || passwordProfessional.isEmpty()) { 
			// login query list must return only one object
			// password query list must return one or more objects (same password can be used for different users)
			return "Usuario ou senha incorretos";
		}
		
		return "Bem vindo, " + userLoginProfessional.get(0).getName();
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestBody ProfessionalDomain professional) {
		
		professionalDAO.save(professional);

		return "Dados atualizados com sucesso";
	}
}
