package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class SearchService {
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	public List<ProfessionalDomain> search(ProfessionalDomain professional){
		return professionalDAO.findByNameLike(professional.getName().substring(0,1).toUpperCase());
	}
	
}
