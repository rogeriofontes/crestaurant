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

import br.com.ft.crestaurant.models.RestaurantType;
import br.com.ft.crestaurant.services.RestaurantTypeService;
import br.com.ft.crestaurant.web.to.RestaurantTypeTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restaurant-types")
@Api("Serviço de Buscar de Pratos para Restaurantes")
public class RestaurantTypeResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestaurantTypeService restaurantTypeService;

	@ApiOperation(notes = "Returns a Restaurants Type when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Restaurant Types by ID", nickname = "getRestaurantTypeById", tags = {
			"RestaurantType" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = RestaurantType.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = RestaurantType.class),
			@ApiResponse(code = 404, message = "Pet not found", response = RestaurantType.class) })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<RestaurantTypeTO>> list() {
		List<RestaurantTypeTO> restaurantTypes = restaurantTypeService.getAll();
		logger.info("Finded Restaurant Types: %s", !StringUtils.isEmpty(restaurantTypes.toString()));
		return new ResponseEntity<>(restaurantTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RestaurantTypeTO> getById(@PathVariable("id") Long id) {
		RestaurantTypeTO restaurantType = restaurantTypeService.find(id);
		return new ResponseEntity<>(restaurantType, HttpStatus.OK);
	}

	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RestaurantTypeTO> getByName(@PathVariable("name") String name) {
		RestaurantTypeTO restaurantType = restaurantTypeService.findByName(name);
		return new ResponseEntity<>(restaurantType, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "restaurantTypesInCache", allEntries = true)
	public ResponseEntity<RestaurantTypeTO> add(@RequestBody RestaurantTypeTO restaurantType) {
		RestaurantTypeTO saved = restaurantTypeService.save(restaurantType);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "restaurantTypesInCache", allEntries = true)
	public ResponseEntity<RestaurantTypeTO> update(@PathVariable("id") Long id,
			@RequestBody RestaurantTypeTO restaurantType) {
		RestaurantTypeTO saved = restaurantTypeService.edit(restaurantType, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "restaurantTypesInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		restaurantTypeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
