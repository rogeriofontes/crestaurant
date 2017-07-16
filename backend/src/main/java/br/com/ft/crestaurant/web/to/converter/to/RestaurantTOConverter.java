package br.com.ft.crestaurant.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.web.to.RestaurantTO;

@Component
public class RestaurantTOConverter implements Converter<Restaurant, RestaurantTO> {

	@Override
	public RestaurantTO convert(Restaurant source) {
		RestaurantTO target = new RestaurantTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setAddress(source.getAddress());
		target.setRate(source.getRate());
		target.setRestaurantType(source.getRestaurantType());
	
		return target;
	}

}
