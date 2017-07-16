package br.com.ft.crestaurant.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "plate")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "onMenu" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plate extends AudityEntity {

	private static final long serialVersionUID = 4795974382859364322L;

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String description;
	@Getter
	@Setter
	@Column(name="on_menu")
	private boolean onMenu;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	public void update(Plate plate) {
		this.name = plate.getName();
		this.description = plate.getDescription();
		this.onMenu = plate.isOnMenu();
		this.restaurant = plate.getRestaurant();
	}

}
