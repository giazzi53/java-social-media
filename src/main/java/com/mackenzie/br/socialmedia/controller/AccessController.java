package com.mackenzie.br.socialmedia.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.MongoDBDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@RestController
public class AccessController {

	@Autowired
	ProfessionalDomain professional;
	
	@Autowired
	MongoDBDAO mongoDB;

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@RequestParam String name, @RequestParam int age, @RequestParam String userLogin,
			@RequestParam String password, @RequestParam Date birthDate, @RequestParam Date careerDate,
			@RequestParam InstructionLevelEnum instructionLevel, @RequestParam ProfileTypeEnum profileType) {

		professional.setName(name);
		professional.setUserLogin(userLogin);
		professional.setPassword(password);
		professional.setBirthDate(birthDate);
		professional.setCareerDate(careerDate);
		professional.setInstructionLevel(instructionLevel);
		professional.setProfileType(profileType);
		
		//mongoDB.store(professional);
		
		return "Cadastro realizado com sucesso";
	}
}
