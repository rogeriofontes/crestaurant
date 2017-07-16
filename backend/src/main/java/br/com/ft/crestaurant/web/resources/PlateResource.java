package br.com.ft.crestaurant.web.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.ft.crestaurant.models.Plate;
import br.com.ft.crestaurant.services.PlateService;
import br.com.ft.crestaurant.web.to.PlateTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/plates")
public class PlateResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlateService plateService;

	@ApiOperation(notes = "Returns a Plates  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Plate s by ID", nickname = "getPlateById", tags = {
			"Plate" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Plate.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Plate.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Plate.class) })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<PlateTO>> list() {
		List<PlateTO> plates = plateService.getAll();
		logger.info("Finded Plate s: %s", !StringUtils.isEmpty(plates.toString()));
		return new ResponseEntity<>(plates, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<PlateTO> getById(@PathVariable("id") Long id) {
		PlateTO plate = plateService.find(id);
		return new ResponseEntity<>(plate, HttpStatus.OK);
	}

	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<PlateTO> getByName(@PathVariable("name") String name) {
		PlateTO plate = plateService.findByName(name);
		return new ResponseEntity<>(plate, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "platesInCache", allEntries = true)
	public ResponseEntity<PlateTO> add(@RequestBody PlateTO plate) {
		PlateTO saved = plateService.save(plate);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "platesInCache", allEntries = true)
	public ResponseEntity<PlateTO> update(@PathVariable("id") Long id,
			@RequestBody PlateTO plate) {
		PlateTO saved = plateService.edit(plate, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "platesInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		plateService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
