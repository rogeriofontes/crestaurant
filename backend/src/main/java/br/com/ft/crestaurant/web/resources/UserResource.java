package br.com.ft.crestaurant.web.resources;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.ft.crestaurant.models.User;
import br.com.ft.crestaurant.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		User user = userRepository.findByUsername(username);
		logger.info("Finded user: %s", !StringUtils.isEmpty(user.toString()));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
