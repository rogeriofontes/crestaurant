package br.com.ft.crestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ft.crestaurant.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	Restaurant findByName(String name);
}
