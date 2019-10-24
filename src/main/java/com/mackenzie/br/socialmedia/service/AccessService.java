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

	public ProfessionalDomain signUp(ProfessionalDomain professional) throws IllegalArgumentException {

		if (validateUser(professional.getUserLogin())) {

			throw new IllegalArgumentException("Usuário já em uso");
		}

		ProfessionalDomain databaseProfessional = professionalDAO.insert(professional);
		PaymentInfoDomain paymentInfo = professional.getPaymentInfo();

		if (paymentInfo != null) {

			paymentInfo.setProfessionalID(databaseProfessional.getProfessionalID());
			paymentInfoDAO.insert(paymentInfo);
		}

		return databaseProfessional;
	}

	public ProfessionalDomain login(ProfessionalDomain professional) throws IllegalAccessException {

		validationUtils.validateProfessionalByUserLoginAndPassword(professionalDAO, professional.getUserLogin(),
				professional.getPassword());

		ProfessionalDomain databaseProfessional = professionalDAO
				.findByUserLoginAndPassword(professional.getUserLogin(), professional.getPassword());

		return databaseProfessional;
	}

	public ProfessionalDomain updateProfile(ProfessionalDomain professional) throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, professional.getProfessionalID());

		ProfessionalDomain databaseProfessional = professionalDAO
				.findByProfessionalID(professional.getProfessionalID());

		databaseProfessional.setName(professional.getName());
		databaseProfessional.setBirthDate(professional.getBirthDate());
		databaseProfessional.setCareerDate(professional.getCareerDate());
		databaseProfessional.setCity(professional.getCity());
		databaseProfessional.setEmail(professional.getEmail());
		databaseProfessional.setInstructionLevel(professional.getInstructionLevel());
		databaseProfessional.getJobRole().setCompanyName(professional.getJobRole().getCompanyName());
		databaseProfessional.getJobRole().setSalary(professional.getJobRole().getSalary());
		databaseProfessional.getJobRole().setJobTitle(professional.getJobRole().getJobTitle());
		databaseProfessional.setPassword(professional.getPassword());
		databaseProfessional.setState(professional.getState());
		databaseProfessional.setUserLogin(professional.getUserLogin());
		databaseProfessional.getPaymentInfo().setCardName(professional.getPaymentInfo().getCardName());
		databaseProfessional.getPaymentInfo().setCardNumber(professional.getPaymentInfo().getCardNumber());
		databaseProfessional.getPaymentInfo().setCardSecurityCode(professional.getPaymentInfo().getCardSecurityCode());
		databaseProfessional.getPaymentInfo().setCardValidationDate(professional.getPaymentInfo().getCardValidationDate().toString());
		databaseProfessional.setProfileImage(professional.getProfileImage());
		databaseProfessional.setProfileType(professional.getProfileType());
		
		professionalDAO.save(databaseProfessional);

		return databaseProfessional;
	}

	public ProfessionalDomain retrieveProfessionalData(String professionalID) throws IllegalArgumentException {

		validationUtils.validateProfessionalByID(professionalDAO, professionalID);

		return professionalDAO.findByProfessionalID(professionalID);
	}

	public boolean validateUser(String userLogin) {

		for (ProfessionalDomain professional : professionalDAO.findAll()) {

			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {

				return true;
			}
		}

		return false;
	}

}
