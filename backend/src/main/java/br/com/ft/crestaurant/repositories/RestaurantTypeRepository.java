package br.com.ft.crestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ft.crestaurant.models.RestaurantType;

public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {
	RestaurantType findByName(String name);
}
