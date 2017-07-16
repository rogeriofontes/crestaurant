package br.com.ft.crestaurant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.ft.crestaurant.web.exceptions.NotImplementationConstructionException;

public class PasswordCrypto {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static PasswordCrypto instance;
    
    private PasswordCrypto(){
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

    public static PasswordCrypto getInstance() {
        if (instance == null) {
            instance = new PasswordCrypto();
        }

        return instance;
    }

    public String encrypt(String str) {
        return passwordEncoder.encode(str);
    }
}