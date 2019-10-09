package com.mackenzie.br.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.PaymentInfoDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.PaymentInfoDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

@Service
public class AccessService {
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired 
	private PaymentInfoDAO paymentInfoDAO;
	
	@Autowired
	private ValidationUtils validationUtils;

	public ProfessionalDomain signUp(ProfessionalDomain professional) throws IllegalArgumentException{
		
		if (validateUser(professional.getUserLogin())) {
			
			throw new IllegalArgumentException("Usuário já em uso");
		}
		
		ProfessionalDomain databaseProfessional = professionalDAO.insert(professional);	
		PaymentInfoDomain paymentInfo = professional.getPaymentInfo();
		
		if(paymentInfo != null) {
			
			paymentInfo.setProfessionalID(databaseProfessional.getProfessionalID());
			paymentInfoDAO.insert(paymentInfo);	
		}
		
		return databaseProfessional;
	}

	public ProfessionalDomain login(ProfessionalDomain professional) throws IllegalAccessException {
		
		validationUtils.validateProfessionalByUserLoginAndPassword(professionalDAO, professional.getUserLogin(), professional.getPassword());
		
		ProfessionalDomain databaseProfessional = professionalDAO.findByUserLoginAndPassword(professional.getUserLogin(), professional.getPassword());
		
		return databaseProfessional;
	}

	public ProfessionalDomain updateProfile(ProfessionalDomain professional) throws IllegalArgumentException{
		
		validationUtils.validateProfessionalByID(professionalDAO, professional.getProfessionalID());
		
		ProfessionalDomain databaseProfessional = professionalDAO.findByProfessionalID(professional.getProfessionalID());
		
		if (professional.getName() != null){
			
			databaseProfessional.setName(professional.getName());
		}
		
		if (professional.getBirthDate() != null){
		
			databaseProfessional.setBirthDate(professional.getBirthDate());
		}
		
		if (professional.getCareerDate() != null){
		
			databaseProfessional.setCareerDate(professional.getCareerDate());
		}
		
		if (professional.getCity() != null){
		
			databaseProfessional.setCity(professional.getCity());
		}
		
		if (professional.getInstructionLevel() != null){
		
			databaseProfessional.setInstructionLevel(professional.getInstructionLevel());
		}
		
		if (professional.getJobRole() != null) {
		
			if (professional.getJobRole().getCompanyName() != null){
			
				databaseProfessional.getJobRole().setCompanyName(professional.getJobRole().getCompanyName());
			}
			
			if (professional.getJobRole().getSalary() != 0){
			
				databaseProfessional.getJobRole().setSalary(professional.getJobRole().getSalary());
			}
		}
		
		if (professional.getPassword() != null){
			databaseProfessional.setPassword(professional.getPassword());
		}
		
		if (professional.getState() != null){
		
			databaseProfessional.setState(professional.getState());
		}
		
		if (professional.getUserLogin() != null){
			
			databaseProfessional.setUserLogin(professional.getUserLogin());
		}
		
		professionalDAO.save(databaseProfessional);

		return databaseProfessional;
	}

	public ProfessionalDomain retrieveProfessionalData(String professionalID) throws IllegalArgumentException{
		
		validationUtils.validateProfessionalByID(professionalDAO, professionalID);
		
		return professionalDAO.findByProfessionalID(professionalID);
	}

	public boolean validateUser(String userLogin) {
		
		for(ProfessionalDomain professional : professionalDAO.findAll()){
			
			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
			
				return true;
			}
		}
		
		return false;
	}
	
}
