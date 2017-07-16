package br.com.ft.crestaurant.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.repositories.RestaurantRepository;
import br.com.ft.crestaurant.services.RestaurantService;
import br.com.ft.crestaurant.web.to.RestaurantTO;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public RestaurantTO save(RestaurantTO restaurantTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(restaurantTO.toString()));
		Restaurant resultSaved = restaurantRepository.save(conversionService.convert(restaurantTO, Restaurant.class));
		return conversionService.convert(resultSaved, RestaurantTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Restaurant result = restaurantRepository.findOne(id);
		if (result != null) {
			restaurantRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public RestaurantTO edit(RestaurantTO restaurantTO, Long id) {
		Restaurant result = restaurantRepository.findOne(id);
		
		Restaurant restaurant = conversionService.convert(restaurantTO, Restaurant.class);
		result.update(restaurant);
		
		Restaurant resultUpdated = restaurantRepository.save(result);
		return conversionService.convert(resultUpdated, RestaurantTO.class);
	}

	@Override
	public RestaurantTO find(Long id) {
		Restaurant result = restaurantRepository.findOne(id);
		return conversionService.convert(result, RestaurantTO.class);
	}

	@Override
	@Cacheable("restaurantsInCache")
	public List<RestaurantTO> getAll() {
		List<RestaurantTO> tos = new ArrayList<>();
		Iterable<Restaurant> itr = restaurantRepository.findAll();
		for (Restaurant restaurant : itr) {
			tos.add(conversionService.convert(restaurant, RestaurantTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("restaurantsInCache")
	public Page<RestaurantTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}

	@Override
	public RestaurantTO findByName(String name) {
		Restaurant result = restaurantRepository.findByName(name);
		return conversionService.convert(result, RestaurantTO.class);
	}


}
