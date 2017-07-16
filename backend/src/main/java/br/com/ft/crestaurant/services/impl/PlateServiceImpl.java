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

import br.com.ft.crestaurant.models.Plate;
import br.com.ft.crestaurant.repositories.PlateRepository;
import br.com.ft.crestaurant.services.PlateService;
import br.com.ft.crestaurant.web.to.PlateTO;

@Service
public class PlateServiceImpl implements PlateService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlateRepository plateRepository;

	@Autowired
	private ConversionService conversionService;

	@Override
	public PlateTO save(PlateTO plateTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(plateTO.toString()));
		Plate resultSaved = plateRepository.save(conversionService.convert(plateTO, Plate.class));
		return conversionService.convert(resultSaved, PlateTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Plate result = plateRepository.findOne(id);
		if (result != null) {
			plateRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public PlateTO edit(PlateTO plateTO, Long id) {
		Plate result = plateRepository.findOne(id);

		Plate plate = conversionService.convert(plateTO, Plate.class);
		result.update(plate);

		Plate resultUpdated = plateRepository.save(result);
		return conversionService.convert(resultUpdated, PlateTO.class);
	}

	@Override
	public PlateTO find(Long id) {
		Plate result = plateRepository.findOne(id);
		return conversionService.convert(result, PlateTO.class);
	}

	@Override
	@Cacheable("platesInCache")
	public List<PlateTO> getAll() {
		List<PlateTO> tos = new ArrayList<>();
		Iterable<Plate> itr = plateRepository.findAll();
		for (Plate plate : itr) {
			tos.add(conversionService.convert(plate, PlateTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("platesInCache")
	public Page<PlateTO> findAllPageable(Pageable pageable) {
		// return examTypeRepository.findAll(pageable);
		return null;
	}

	@Override
	public PlateTO findByName(String name) {
		Plate result = plateRepository.findByName(name);
		return conversionService.convert(result, PlateTO.class);
	}
}
