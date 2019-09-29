package com.mackenzie.br.socialmedia.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mackenzie.br.socialmedia.domain.PublicationReactionDomain;

@Repository
public interface PublicationReactionDAO extends MongoRepository<PublicationReactionDomain, String>{

	boolean existsByProfessionalIDAndPublicationID(String professionalID, String publicaitonID);

}
