package br.com.ft.crestaurant.services;

import br.com.ft.crestaurant.web.to.RestaurantTO;

public interface RestaurantService extends CrudService<RestaurantTO, Long> {

	RestaurantTO findByName(String name);

	RestaurantTO getRestaurantPlatesById(Long id);
	
}
