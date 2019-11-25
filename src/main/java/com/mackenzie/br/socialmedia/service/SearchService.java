package com.mackenzie.br.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.utils.NameUtils;

@Service
public class SearchService {
	
	@Autowired
	private ProfessionalDAO professionalDAO;
	
	@Autowired
	private NameUtils nameUtils;
	
	public List<ProfessionalDomain> search(String professionalName){
		
		return professionalDAO.findByNameLike(nameUtils.capitailizeWord(professionalName.toLowerCase()));
	}
	
}
