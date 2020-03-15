package com.api.practica.productos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.exceptions.ResourceNotFoundException;

@Service
public class ProductoBusiness {

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	ModelMapperWrapper modelMapper;

	public List<ProductoDto> getProductos() {
		return Arrays.asList(this.modelMapper.map(this.productoRepository.findAll(), ProductoDto[].class));
	}

	public ProductoDto saveProducto(ProductoDto p) {
		Producto producto = this.productoRepository.save(modelMapper.map(p, Producto.class));

		return this.modelMapper.map(producto, ProductoDto.class);
	}

	public ProductoDto getProductoById(Long id) throws ResourceNotFoundException {
		Optional<Producto> p = this.productoRepository.findById(id);

		if (!p.isPresent()) {
			throw new ResourceNotFoundException("Producto no encontrado id: " + id);
		}

		Producto producto = p.get();
		ProductoDto productoDto = this.modelMapper.map(producto, ProductoDto.class);

		return productoDto;
	}

	public List<ProductoDto> searchProductos(String text) {	
		return Arrays.asList(this.modelMapper.map(this.productoRepository.findAllByNombreContaining(text), ProductoDto[].class));
	}

}
