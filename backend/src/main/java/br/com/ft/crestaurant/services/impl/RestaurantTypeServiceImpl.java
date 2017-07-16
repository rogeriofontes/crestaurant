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

import br.com.ft.crestaurant.models.RestaurantType;
import br.com.ft.crestaurant.repositories.RestaurantTypeRepository;
import br.com.ft.crestaurant.services.RestaurantTypeService;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;

@Service
public class RestaurantTypeServiceImpl implements RestaurantTypeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestaurantTypeRepository restaurantTypeRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public RestaurantTypeTO save(RestaurantTypeTO restaurantTypeTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(restaurantTypeTO.toString()));
		RestaurantType resultSaved = restaurantTypeRepository.save(conversionService.convert(restaurantTypeTO, RestaurantType.class));
		return conversionService.convert(resultSaved, RestaurantTypeTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		RestaurantType result = restaurantTypeRepository.findOne(id);
		if (result != null) {
			restaurantTypeRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public RestaurantTypeTO edit(RestaurantTypeTO restaurantTypeTO, Long id) {
		RestaurantType result = restaurantTypeRepository.findOne(id);
		
		RestaurantType restaurantType = conversionService.convert(restaurantTypeTO, RestaurantType.class);
		result.update(restaurantType);
		
		RestaurantType resultUpdated = restaurantTypeRepository.save(result);
		return conversionService.convert(resultUpdated, RestaurantTypeTO.class);
	}

	@Override
	public RestaurantTypeTO find(Long id) {
		RestaurantType result = restaurantTypeRepository.findOne(id);
		return conversionService.convert(result, RestaurantTypeTO.class);
	}

	@Override
	@Cacheable("restaurantTypesInCache")
	public List<RestaurantTypeTO> getAll() {
		List<RestaurantTypeTO> tos = new ArrayList<>();
		Iterable<RestaurantType> itr = restaurantTypeRepository.findAll();
		for (RestaurantType restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, RestaurantTypeTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("restaurantTypesInCache")
	public Page<RestaurantTypeTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}

	@Override
	public RestaurantTypeTO findByName(String name) {
		RestaurantType result = restaurantTypeRepository.findByName(name);
		return conversionService.convert(result, RestaurantTypeTO.class);
	}

}
