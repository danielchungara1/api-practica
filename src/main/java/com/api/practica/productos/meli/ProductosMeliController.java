package com.api.practica.productos.meli;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/productos/meli")
public class ProductosMeliController {
	
	@Autowired
	ProductoMeliBusiness productoBusiness;

	@GetMapping(value = "")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAnyAuthority('USUARIO')")
	@ApiOperation(value = "${ProductosMeliController.listarProductosPorNombre}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public List<ProductoMeliDto> listarProductosPorNombre(
			@RequestParam (required = true) String nombre,
			@RequestParam (required = true) Integer limit,
			@RequestParam (required = true) Integer offset) throws Exception {
		return this.productoBusiness.getProductosPorNombre(nombre, limit, offset);
	}

	
}
