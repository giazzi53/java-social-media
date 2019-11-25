package com.mackenzie.br.socialmedia.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.PaymentInfoDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.PaymentInfoDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.utils.NameUtils;
import com.mackenzie.br.socialmedia.utils.ValidationUtils;

@Service
public class AccessService {

	@Autowired
	private ProfessionalDAO professionalDAO;

	@Autowired
	private PaymentInfoDAO paymentInfoDAO;

	@Autowired
	private ValidationUtils validationUtils;
	
	@Autowired
	private NameUtils nameUtils;
	
	public ProfessionalDomain signUp(ProfessionalDomain professional) throws IllegalArgumentException {

		if (validateUser(professional.getUserLogin())) {

			throw new IllegalArgumentException("Usuário já em uso");
		}
		
		professional.setName(nameUtils.capitailizeWord(professional.getName().toLowerCase()));
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

		databaseProfessional.setName(nameUtils.capitailizeWord(professional.getName().toLowerCase()));
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
		
		if(professional.getPaymentInfo() != null) {
			PaymentInfoDomain newPaymentInfo = new PaymentInfoDomain();
			
			newPaymentInfo.setCardName(professional.getPaymentInfo().getCardName());
			newPaymentInfo.setCardNumber(professional.getPaymentInfo().getCardNumber());
			newPaymentInfo.setCardSecurityCode(professional.getPaymentInfo().getCardSecurityCode());
			
			Date date = professional.getPaymentInfo().getCardValidationDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int validationDateYYYY = calendar.get(Calendar.YEAR);
			int validationDateMM = calendar.get(Calendar.MONTH) + 1;
			String cardValidationDateString = Integer.toString(validationDateYYYY) + '-' + Integer.toString(validationDateMM); 
			newPaymentInfo.setCardValidationDate(cardValidationDateString);
			databaseProfessional.setPaymentInfo(newPaymentInfo);
		}
		
		if(professional.getProfileImage() != null) {
			databaseProfessional.setProfileImage(professional.getProfileImage());
		}
		
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
