package br.com.ft.crestaurant.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.RestaurantType;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;

@Component
public class RestaurantTypeTOConverter implements Converter<RestaurantType, RestaurantTypeTO> {

	@Override
	public RestaurantTypeTO convert(RestaurantType source) {
		RestaurantTypeTO target = new RestaurantTypeTO();

		target.setId(source.getId());
		target.setName(source.getName());

		return target;
	}

}
