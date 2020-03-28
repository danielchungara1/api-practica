package com.api.practica.usuario;

import com.api.practica.commons.entity.BaseEntity;
import com.api.practica.security.Rol;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Rol> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
}
