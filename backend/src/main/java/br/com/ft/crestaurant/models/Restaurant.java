package br.com.ft.crestaurant.models;

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
@Table(name = "restaurant")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "address", "rate" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant extends AudityEntity {

	private static final long serialVersionUID = 1816366153871014861L;

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String description;
	@Getter
	@Setter
	private String address;
	@Getter
	@Setter
	private int rate;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "restaurant_type_id")
	private RestaurantType restaurantType;
	
	public void update(Restaurant restaurant) {
		this.name = restaurant.getName();
		this.description = restaurant.getDescription();
		this.address = restaurant.getAddress();
		this.rate = restaurant.getRate();
		this.restaurantType = restaurant.getRestaurantType();
	}

}
