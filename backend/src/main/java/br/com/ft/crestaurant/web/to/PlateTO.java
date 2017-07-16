package br.com.ft.crestaurant.web.to;

import java.io.Serializable;

import br.com.ft.crestaurant.models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "description", "onMenu", "restaurant" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlateTO implements Serializable {
	private static final long serialVersionUID = -906247150054774590L;

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
	private boolean onMenu;
	@Getter
	@Setter
	private Restaurant restaurant;
}
