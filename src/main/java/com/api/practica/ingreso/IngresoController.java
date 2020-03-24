package com.api.practica.ingreso;

import com.api.practica.usuario.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ingreso")
public class IngresoController {
	
	@Autowired
	IngresoBusiness ingresoBusiness;

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public UsuarioDto autenticar(@RequestBody UsuarioDto usuario) {
		return this.ingresoBusiness.autenticar(usuario);
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public UsuarioDto registrar(@RequestBody UsuarioDto usuario) {
		return this.ingresoBusiness.registrar(usuario);
	}
	
}
