package br.com.ft.crestaurant.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.web.to.RestaurantTO;

@Component
public class RestaurantConverter implements Converter<RestaurantTO, Restaurant> {

	@Override
	public Restaurant convert(RestaurantTO source) {
		Restaurant target = new Restaurant();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setAddress(source.getAddress());
		target.setRate(source.getRate());
		target.setRestaurantType(source.getRestaurantType());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity

		return target;
	}

}
