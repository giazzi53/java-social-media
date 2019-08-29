package com.mackenzie.br.socialmedia.controller;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.JobRoleDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@RestController
public class AccessController {

	@Autowired
	ProfessionalDomain professional;

	@Autowired
	ProfessionalDAO mongoDB;

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@RequestParam String name, @RequestParam int age, @RequestParam String userLogin,
			@RequestParam String password, @RequestParam File profileImage, @RequestParam Date birthDate,
			@RequestParam Date careerDate, @RequestParam JobRoleDomain jobRole,
			@RequestParam InstructionLevelEnum instructionLevel, @RequestParam ProfileTypeEnum profileType) {

		professional.setName(name);
		professional.setUserLogin(userLogin);
		professional.setPassword(password);
		professional.setProfileImage(profileImage);
		professional.setBirthDate(birthDate);
		professional.setCareerDate(careerDate);
		professional.setJobRole(jobRole);
		professional.setInstructionLevel(instructionLevel);
		professional.setProfileType(profileType);

		// mongoDB.insert(professional);

		return "Cadastro realizado com sucesso";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam String userLogin, @RequestParam String password) {
		boolean authorized = mongoDB.queryForAuthorization(userLogin, password);

		if (!authorized) {
			return "Usuario ou senha incorretos";
		}
		return "Bem vindo " + userLogin;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestParam String name, @RequestParam int age, @RequestParam String userLogin,
			@RequestParam String password, @RequestParam File profileImage, @RequestParam Date birthDate,
			@RequestParam Date careerDate, @RequestParam JobRoleDomain jobRole,
			@RequestParam InstructionLevelEnum instructionLevel, @RequestParam ProfileTypeEnum profileType) {

		professional.setName(name);
		professional.setUserLogin(userLogin);
		professional.setPassword(password);
		professional.setProfileImage(profileImage);
		professional.setBirthDate(birthDate);
		professional.setCareerDate(careerDate);
		professional.setJobRole(jobRole);
		professional.setInstructionLevel(instructionLevel);
		professional.setProfileType(profileType);

		// mongodb.updateProfile(professional);

		return "Dados atualizados com sucesso";
	}
}
