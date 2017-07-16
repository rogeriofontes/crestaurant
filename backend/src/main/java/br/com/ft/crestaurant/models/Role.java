package br.com.ft.crestaurant.models;

public enum Role {

	ADMIN("ADMIN"), USER("USER");

	private String roleDescription;

	private Role(String role) {
		this.roleDescription = role;
	}

	public String getRole() {
		return roleDescription;
	}

}
