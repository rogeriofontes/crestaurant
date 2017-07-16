package br.com.ft.crestaurant.services;

import br.com.ft.crestaurant.web.to.RestaurantTypeTO;

public interface RestaurantTypeService extends CrudService<RestaurantTypeTO, Long> {

	RestaurantTypeTO findByName(String name);
	
}
