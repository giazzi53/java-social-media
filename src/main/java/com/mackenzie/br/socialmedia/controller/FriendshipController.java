package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;
import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@RestController
public class FriendshipController {
	
	@Autowired
	FriendshipDAO friendshipDAO;
	
	@Autowired
	ProfessionalDAO professionalDAO;

	@RequestMapping(value = "/sendFriendshipRequest", method = RequestMethod.POST)
	public String sendFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		professional1.get(0).getListOfFriendRequests().add(professional0.get(0));
		professionalDAO.save(professional1.get(0));
		
		return "Solicitação de amizade enviada";
	}
	
	@RequestMapping(value = "/acceptFriendshipRequest", method = RequestMethod.POST)
	public String acceptFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {

			List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
			professional0.get(0).getListOfFriends().add(professionalDAO.findByUserLogin(professionals.get(1).getUserLogin()).get(0));
			professionalDAO.save(professional0.get(0));
			
			List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
			professional1.get(0).getListOfFriends().add(professionalDAO.findByUserLogin(professionals.get(0).getUserLogin()).get(0));
			professionalDAO.save(professional1.get(0));
			
		return "Solicitação de amizade aceita";
	}
	
	@RequestMapping(value = "/rejectFriendshipRequest", method = RequestMethod.POST)
	public String rejectFriendshipRequest(@RequestBody List<ProfessionalDomain> professionals) {
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		List<ProfessionalDomain> listRequest = professional0.get(0).getListOfFriendRequests();
		
		for (ProfessionalDomain request : listRequest){
			if (request.getUserLogin().equalsIgnoreCase(professional1.get(0).getUserLogin())) {
				listRequest.remove(request);
				professionalDAO.save(professional0.get(0));
				break;
			}
		}
		
		return "Solicitação de amizade recusada";
	}
}
