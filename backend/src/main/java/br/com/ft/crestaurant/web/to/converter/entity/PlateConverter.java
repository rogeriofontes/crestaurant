package br.com.ft.crestaurant.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.ft.crestaurant.models.Plate;
import br.com.ft.crestaurant.web.to.PlateTO;

@Component
public class PlateConverter implements Converter<PlateTO, Plate> {

	@Override
	public Plate convert(PlateTO source) {
		Plate target = new Plate();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setOnMenu(source.isOnMenu());
		target.setRestaurant(source.getRestaurant());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
		
		return target;
	}

}
