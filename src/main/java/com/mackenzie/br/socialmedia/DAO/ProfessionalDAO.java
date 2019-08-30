package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Repository
public interface ProfessionalDAO extends MongoRepository<ProfessionalDomain, String>{
	
	String findByUserLogin(String userLogin);
	
	String findByPassword(String passsword);

}
