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

import br.com.ft.crestaurant.models.Restaurant;
import br.com.ft.crestaurant.services.RestaurantService;
import br.com.ft.crestaurant.web.to.RestaurantTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restaurants")
public class RestaurantResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestaurantService restaurantService;

	@ApiOperation(notes = "Returns a Restaurants  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Restaurant s by ID", nickname = "getRestaurantById", tags = {
			"Restaurant" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Restaurant.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Restaurant.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Restaurant.class) })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<RestaurantTO>> list() {
		List<RestaurantTO> restaurants = restaurantService.getAll();
		logger.info("Finded Restaurant s: %s", !StringUtils.isEmpty(restaurants.toString()));
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RestaurantTO> getById(@PathVariable("id") Long id) {
		RestaurantTO restaurant = restaurantService.find(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/plates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RestaurantTO> getRestaurantPlatesById(@PathVariable("id") Long id) {
		RestaurantTO restaurant = restaurantService.getRestaurantPlatesById(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RestaurantTO> getByName(@PathVariable("name") String name) {
		RestaurantTO restaurant = restaurantService.findByName(name);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "restaurantsInCache", allEntries = true)
	public ResponseEntity<RestaurantTO> add(@RequestBody RestaurantTO restaurant) {
		RestaurantTO saved = restaurantService.save(restaurant);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "restaurantsInCache", allEntries = true)
	public ResponseEntity<RestaurantTO> update(@PathVariable("id") Long id,
			@RequestBody RestaurantTO restaurant) {
		RestaurantTO saved = restaurantService.edit(restaurant, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "restaurantsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		restaurantService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
