package br.com.ft.crestaurant.services;

import br.com.ft.crestaurant.models.User;
import br.com.ft.crestaurant.web.to.CurrentUser;

public interface CurrentUserService {
	CurrentUser getDataToCurrentUser();

	User loadCurrentUser();
}
