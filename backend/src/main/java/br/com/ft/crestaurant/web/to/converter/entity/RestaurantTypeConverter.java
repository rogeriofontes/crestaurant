package br.com.ft.crestaurant.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.RestaurantType;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;

@Component
public class RestaurantTypeConverter implements Converter<RestaurantTypeTO, RestaurantType> {

	@Override
	public RestaurantType convert(RestaurantTypeTO source) {
		RestaurantType target = new RestaurantType();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
