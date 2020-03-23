package com.api.practica.ingreso;

import com.api.practica.usuario.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ingreso")
public class IngresoController {
	
	@Autowired
	IngresoBusiness ingresoBusiness;

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public UsuarioDto autenticar(@RequestBody UsuarioDto usuario) {
		return this.ingresoBusiness.autenticar(usuario);
	}

	@RequestMapping(value = "/sing-in", method = RequestMethod.POST)
	public UsuarioDto singIn(@RequestBody UsuarioDto usuario) {
		return this.ingresoBusiness.signIn(usuario);
	}


//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ProductoDto saveProducto(@RequestBody ProductoDto newProducto) {
//		return this.productoBusiness.saveProducto(newProducto);
//	}
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public List<ProductoDto> listaProductos() {
//		return this.productoBusiness.getProductos();
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ProductoDto getProducto(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
//		return this.productoBusiness.getProductoById(id);
//	}
//
//	@RequestMapping(value = "/search-products", method = RequestMethod.GET)
//	public List<ProductoDto> searchProductos(@RequestParam String text) throws ResourceNotFoundException {
//		return this.productoBusiness.searchProductos(text);
//	}
//
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	public void deleteProductoById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
//		this.productoBusiness.deleteProductoById(id);
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
//	public ProductoDto updateProducto(@RequestBody ProductoDto producto) {
//		return this.productoBusiness.updateProducto(producto);
//	}
	
}
