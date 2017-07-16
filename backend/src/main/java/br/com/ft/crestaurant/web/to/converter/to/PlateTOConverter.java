package br.com.ft.crestaurant.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.Plate;
import br.com.ft.crestaurant.web.to.PlateTO;

@Component
public class PlateTOConverter implements Converter<Plate, PlateTO> {

	@Override
	public PlateTO convert(Plate source) {
		PlateTO target = new PlateTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setOnMenu(source.isOnMenu());
		target.setRestaurant(source.getRestaurant());

		return target;
	}

}
