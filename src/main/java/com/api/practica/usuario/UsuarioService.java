package com.api.practica.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(String textSearch) {
        return this.usuarioRepository.findAll(UsuarioSpecification.getUsersByAll(textSearch));
    }
}
