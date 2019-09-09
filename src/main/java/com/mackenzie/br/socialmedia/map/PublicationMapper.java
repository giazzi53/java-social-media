package com.mackenzie.br.socialmedia.map;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mackenzie.br.socialmedia.domain.PublicationDomain;

@Service
public class PublicationMapper {

	public List<PublicationDomain> mapPublicationListToDescendingOrder(List<PublicationDomain> publicationList) {
		Collections.sort(publicationList, new Comparator<PublicationDomain>() {
			  public int compare(PublicationDomain publication1, PublicationDomain publication2) {
			      return publication1.getPublicationDate().compareTo(publication2.getPublicationDate());
			  }
			});
		Collections.reverse(publicationList);
		return publicationList;
	}

}
