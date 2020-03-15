package com.api.practica.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.practica.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(path = "/productos")
public class ProductoController {
	
	@Autowired
	ProductoBusiness productoBusiness;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ProductoDto> listaProductos() {
		return this.productoBusiness.getProductos();
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ProductoDto saveProducto(@RequestBody ProductoDto newProducto) {
		return this.productoBusiness.saveProducto(newProducto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductoDto getProducto(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
		return this.productoBusiness.getProductoById(id);
	}
	
}
