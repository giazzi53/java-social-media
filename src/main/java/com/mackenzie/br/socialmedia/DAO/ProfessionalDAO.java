package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Repository
public interface ProfessionalDAO extends MongoRepository<ProfessionalDomain, String>{
	
	ProfessionalDomain findByUserLogin(String userLogin);
	
	ProfessionalDomain findByPassword(String passsword);
	
	ProfessionalDomain findByProfessionalID(String professionalID);
	
	ProfessionalDomain findByUserLoginAndPassword(String userLogin, String password);
	
	boolean existsByUserLoginAndPassword(String userLogin, String password);

	boolean existsByUserLogin(String userLogin);
	
	boolean existsByProfessionalID(String professionalID);

}
