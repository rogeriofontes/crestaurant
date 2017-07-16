package br.com.ft.crestaurant.services;

import br.com.ft.crestaurant.web.to.PlateTO;

public interface PlateService extends CrudService<PlateTO, Long> {

	PlateTO findByName(String name);
	
}
