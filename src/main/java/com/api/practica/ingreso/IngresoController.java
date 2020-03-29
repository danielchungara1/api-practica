package com.api.practica.ingreso;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ingreso")
public class IngresoController {
	
	@Autowired
	IngresoBusiness ingresoBusiness;

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	@ApiOperation(value = "${IngresoController.autenticar}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid username/password supplied")})
	public TokenDto autenticar(@RequestBody CredencialesDto usuario) {
		return this.ingresoBusiness.autenticar(usuario);
	}


	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	@ApiOperation(value = "${IngresoController.registrar}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid username/password supplied")})
	public void registrar(@RequestBody CredencialesDto usuario) {
		this.ingresoBusiness.registrar(usuario);
	}

}
