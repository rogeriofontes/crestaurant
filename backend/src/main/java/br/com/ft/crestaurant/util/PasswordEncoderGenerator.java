package br.com.ft.crestaurant.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ft.crestaurant.web.exceptions.NotImplementationConstructionException;

public class PasswordEncoderGenerator {
	
	private PasswordEncoderGenerator(){
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

	public static String generator(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}
