package br.com.ft.crestaurant.security;

import br.com.ft.crestaurant.web.exceptions.NotImplementationConstructionException;

public final class AuthoritiesConstants {
	
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";

	private AuthoritiesConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
