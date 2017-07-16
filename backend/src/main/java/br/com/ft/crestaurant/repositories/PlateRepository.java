package br.com.ft.crestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ft.crestaurant.models.Plate;

public interface PlateRepository extends JpaRepository<Plate, Long> {
	Plate findByName(String name);
}
