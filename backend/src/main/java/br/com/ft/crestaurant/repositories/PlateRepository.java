package br.com.ft.crestaurant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ft.crestaurant.models.Plate;

public interface PlateRepository extends JpaRepository<Plate, Long> {
	Plate findByName(String name);
	List<Plate>findByRestaurantId(Long id);
}
