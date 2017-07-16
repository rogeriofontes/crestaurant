package br.com.ft.crestaurant.web.to;

import java.io.Serializable;

import br.com.ft.crestaurant.models.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "description", "address", "rate", "restaurantType" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantTO implements Serializable {
	private static final long serialVersionUID = 7871204684617468815L;

	@Getter
	@Setter
	private Long id;
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
	private RestaurantType restaurantType;
}
