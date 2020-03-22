package com.api.practica.ingreso;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.exceptions.CredencialesInvalidasException;
import com.api.practica.usuario.Usuario;
import com.api.practica.usuario.UsuarioDto;
import com.api.practica.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngresoBusiness {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ModelMapperWrapper modelMapper;

//	public List<UsuarioDto> getProductos() {
//		return Arrays.asList(this.modelMapper.map(this.productoRepository.findAll(), UsuarioDto[].class));
//	}
//
//	public UsuarioDto saveProducto(UsuarioDto p) {
//		Producto producto = this.productoRepository.save(modelMapper.map(p, Producto.class));
//
//		return this.modelMapper.map(producto, UsuarioDto.class);
//	}
//
//	public UsuarioDto getProductoById(Long id) throws ResourceNotFoundException {
//		Optional<Producto> p = this.productoRepository.findById(id);
//
//		if (!p.isPresent()) {
//			throw new ResourceNotFoundException("Producto no encontrado id: " + id);
//		}
//
//		Producto producto = p.get();
//		UsuarioDto productoDto = this.modelMapper.map(producto, UsuarioDto.class);
//
//		return productoDto;
//	}
//
//	public List<UsuarioDto> searchProductos(String text) {
//		return Arrays.asList(this.modelMapper.map(this.productoRepository.findAllByNombreContaining(text), UsuarioDto[].class));
//	}
//
//	public void deleteProductoById(Long id) {
//		try {
//			this.productoRepository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException("Producto no encontrado id: " + id);
//		}
//
//	}
//
//	public UsuarioDto updateProducto(UsuarioDto producto) {
//		// TODO Auto-generated method stub
//		return this.saveProducto(producto);
//	}

	public UsuarioDto autenticar(UsuarioDto usuario) {
		Optional<Usuario> u = this.usuarioRepository.findByEmailAndPassword(usuario.getEmail(), usuario.getPassword());
		if (u.isPresent()){
			return this.modelMapper.map(u.get(), UsuarioDto.class);
		}else {
			throw new CredencialesInvalidasException("Nombre de usuario o password incorrectos.");
		}
	}
}
