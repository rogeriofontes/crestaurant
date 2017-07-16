package br.com.ft.crestaurant.web.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantTypeTO {

	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String name;
}
