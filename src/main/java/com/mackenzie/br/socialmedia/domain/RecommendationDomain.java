package com.mackenzie.br.socialmedia.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RecommendationCollection")
public class RecommendationDomain {
	
	@Id
	private String recomendationID;
	
	@NotNull
	private String recommenderID;
	
	@NotNull
	private String recommendedID;

	public String getRecomendationID() {
		return recomendationID;
	}

	public String getRecommenderID() {
		return recommenderID;
	}

	public void setRecommenderID(String recommenderID) {
		this.recommenderID = recommenderID;
	}

	public String getRecommendedID() {
		return recommendedID;
	}

	public void setRecommendedID(String recommendedID) {
		this.recommendedID = recommendedID;
	}

}
