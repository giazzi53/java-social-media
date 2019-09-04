package com.mackenzie.br.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.DAO.FriendshipDAO;
import com.mackenzie.br.socialmedia.DAO.ProfessionalDAO;
import com.mackenzie.br.socialmedia.domain.FriendshipDomain;
import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@Service
public class FriendshipService {
	
	public FriendshipService() {
		super();
	}

	@Autowired
	ProfessionalDAO professionalDAO;
	
	@Autowired
	FriendshipDAO friendshipDAO;
	
	public String sendFriendshipRequest(List<ProfessionalDomain> professionals) {
		
		if (validateUser(professionals.get(0).getUserLogin()) && validateUser(professionals.get(1).getUserLogin())) {
		}else {
			return "Usuário Inválido";
		}
		
		System.out.println(professionalDAO.existsByUserLogin(professionals.get(0).getUserLogin()));
		System.out.println(professionalDAO.existsByUserLogin(professionals.get(1).getUserLogin()));
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		List<ProfessionalDomain> listRequest0 = professional0.get(0).getListOfFriendRequests();
		List<ProfessionalDomain> listRequest1 = professional1.get(0).getListOfFriendRequests();
		
		for (ProfessionalDomain request : listRequest0){
			if (request.getUserLogin().equalsIgnoreCase(professional1.get(0).getUserLogin())) {
				return "O usuário " + professional1.get(0).getName() + " já lhe enviou uma requisição de amizade.";
			}
		}
		
		for (ProfessionalDomain request : listRequest1){
			if (request.getUserLogin().equalsIgnoreCase(professional0.get(0).getUserLogin())) {
				return "A solicitação de amizade para " +  professional1.get(0).getName() + " está pendente.";
			}
		}
		
		professional1.get(0).getListOfFriendRequests().add(professional0.get(0));
		professionalDAO.save(professional1.get(0));
		
		return "Solicitação de amizade enviada.";
		
	}
	
	public String acceptFriendshipRequest(List<ProfessionalDomain> professionals) {
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		List<ProfessionalDomain> listRequest0 = professional0.get(0).getListOfFriendRequests();
		List<ProfessionalDomain> listFriends0 = professional0.get(0).getListOfFriends();
		
		
		List<ProfessionalDomain> professionalsList = new ArrayList<ProfessionalDomain>();
		professionalsList.add(professional0.get(0));
		professionalsList.add(professional1.get(0));
		
		for (ProfessionalDomain request : listFriends0){
			if (request.getUserLogin().equalsIgnoreCase(professional1.get(0).getUserLogin())) {
				return "O usuário " + professional1.get(0).getName() + " já é seu amigo.";
			}
		}
		
		for (ProfessionalDomain request : listRequest0){
			if (request.getUserLogin().equalsIgnoreCase(professional1.get(0).getUserLogin())) {
				
				professional0.get(0).getListOfFriends().add(professionalDAO.findByUserLogin(professionals.get(1).getUserLogin()).get(0));
				professional0.get(0).getListOfFriendRequests().remove(request);
				professional1.get(0).getListOfFriends().add(professionalDAO.findByUserLogin(professionals.get(0).getUserLogin()).get(0));
				
				FriendshipDomain friendRelationship = new FriendshipDomain();
				friendRelationship.setProfessional1(professional0.get(0));
				friendRelationship.setProfessional2(professional1.get(0));

				friendshipDAO.save(friendRelationship);
				professionalDAO.saveAll(professionalsList);
				
				return "O usuário " + professional1.get(0).getName() + " foi adicionado a sua lista de amigos.";
			}
		}
		
		return "Não existe nenhuma requisição de amizade de : " + professional1.get(0).getName() + ".";
		}

	public String rejectFriendshipRequest(List<ProfessionalDomain> professionals) {
		
		List<ProfessionalDomain> professional0 = professionalDAO.findByUserLogin(professionals.get(0).getUserLogin());
		List<ProfessionalDomain> professional1 = professionalDAO.findByUserLogin(professionals.get(1).getUserLogin());
		
		List<ProfessionalDomain> listRequest = professional0.get(0).getListOfFriendRequests();
		
		for (ProfessionalDomain request : listRequest){
			if (request.getUserLogin().equalsIgnoreCase(professional1.get(0).getUserLogin())) {
				listRequest.remove(request);
				professionalDAO.save(professional0.get(0));
				return "Solicitação de amizade recusada.";
			}
		}
		
		return "Nenhuma solicitação de " + professional1.get(0).getName() + ", foi encontrada.";
	}
	
	public boolean validateUser(String userLogin) {
		for (ProfessionalDomain professional : professionalDAO.findAll()){
			if (professional.getUserLogin().equalsIgnoreCase(userLogin)) {
				return true;
			}
		}
		return false;
	}

}
