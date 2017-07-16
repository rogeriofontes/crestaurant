package br.com.ft.crestaurant.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ft.crestaurant.models.User;
import br.com.ft.crestaurant.services.CurrentUserService;
import br.com.ft.crestaurant.services.CustomUserDetailsService;
import br.com.ft.crestaurant.web.to.CurrentUser;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	public CurrentUser getDataToCurrentUser() {
		User user = this.customUserDetailsService.loadCurrentUser();
		return new CurrentUser(user.getUsername(), user.getEmail(), user.getRoles());
	}

	@Override
	public User loadCurrentUser() {
		return this.customUserDetailsService.loadCurrentUser();
	}

}
