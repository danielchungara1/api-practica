package com.api.practica.productos;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.api.practica.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(path = "/productos")
public class ProductoController {
	
	@Autowired
	ProductoBusiness productoBusiness;

	@GetMapping(value = "")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAnyAuthority('USUARIO')")
	@ApiOperation(value = "${ProductoController.listarProductos}", response = ProductoDto.class)
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public List<ProductoDto> listaProductos() {
		return this.productoBusiness.getProductos();
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAnyAuthority('USUARIO')")
	@ApiOperation(value = "${ProductoController.getProductoById}", response = ProductoDto.class)
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ProductoDto getProducto(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
		return this.productoBusiness.getProductoById(id);
	}

	@GetMapping(value = "/search-products")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAnyAuthority('USUARIO')")
	@ApiOperation(value = "${ProductoController.searchProducto}", response = ProductoDto.class)
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public List<ProductoDto> searchProductos(@RequestParam String text) throws ResourceNotFoundException {
		return this.productoBusiness.searchProductos(text);
	}

	@PostMapping(value = "")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@ApiOperation(value = "${ProductoController.saveProducto}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public void saveProducto(@RequestBody ProductoNuevoDto newProducto) {
		this.productoBusiness.saveProducto(newProducto);
	}


	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@ApiOperation(value = "${ProductoController.deleteProducto}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public void deleteProductoById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
		this.productoBusiness.deleteProductoById(id);
	}


	@PutMapping(value = "/edit")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@ApiOperation(value = "${ProductoController.editProducto}" , response = ProductoDto.class)
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ProductoDto updateProducto(@RequestBody ProductoDto producto) {
		return this.productoBusiness.updateProducto(producto);
	}
	
	
}
