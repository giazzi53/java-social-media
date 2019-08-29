package com.mackenzie.br.socialmedia.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Repository
public class ProfessionalDAO {
	
	
	public void store(ProfessionalDomain professionalDomain) {
		
	}
	
	public boolean queryForAuthorization(String userLogin, String password) {
		return true;
	}
	
	public void updateProfile(ProfessionalDomain professionalDomain) {
	
	}

}
