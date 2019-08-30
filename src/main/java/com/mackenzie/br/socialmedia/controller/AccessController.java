package com.mackenzie.br.socialmedia.controller;

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
		
		String userLoginInput = professionalDAO.findByUserLogin(professional.getUserLogin());
		String passwordInput = professionalDAO.findByPassword(professional.getPassword());
		
		if (!userLoginInput.equals(professional.getUserLogin())) {
			return "Usuario incorreto";
		} else if(!passwordInput.equals(professional.getPassword())) {
			return "Senha incorreta";
		}
		
		return "Bem vindo, " + professional.getName();
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestBody ProfessionalDomain professional) {
		
		professionalDAO.save(professional);

		return "Dados atualizados com sucesso";
	}
}
