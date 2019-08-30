package com.mackenzie.br.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mackenzie.br.socialmedia.domain.ProfessionalDomain;

@SpringBootApplication
public class SocialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialmediaApplication.class, args);
	}
	
//	@Bean
//	public ProfessionalDomain professionalDomain() {
//		return new ProfessionalDomain();
//	}

}
