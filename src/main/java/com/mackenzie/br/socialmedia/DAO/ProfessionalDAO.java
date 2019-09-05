package com.mackenzie.br.socialmedia.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Repository
public interface ProfessionalDAO extends MongoRepository<ProfessionalDomain, String>{
	
	List<ProfessionalDomain> findByUserLogin(String userLogin);
	
	List<ProfessionalDomain> findByPassword(String passsword);
	
	List<ProfessionalDomain> findByProfessionalID(String professionalID);

	boolean existsByUserLogin(String userLogin);

}
