package com.api.practica.ingreso;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.commons.PasswordManager;
import com.api.practica.exceptions.CredencialesInvalidasException;
import com.api.practica.exceptions.EmailExistenteException;
import com.api.practica.usuario.Usuario;
import com.api.practica.usuario.UsuarioDto;
import com.api.practica.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngresoBusiness {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapperWrapper modelMapper;

    @Autowired
    PasswordManager passwordManager;

    public UsuarioDto autenticar(UsuarioDto usuario) {
        Optional<Usuario> u = this.usuarioRepository.findByEmail(usuario.getEmail());
        if (!u.isPresent()) {
            throw new CredencialesInvalidasException("Usuario invalido.");
        }

        if (this.passwordManager.matches(usuario.getPassword(), u.get().getPassword())){
            throw new CredencialesInvalidasException("Password invalido.");
        }

        return this.modelMapper.map(u.get(), UsuarioDto.class);
    }

    public UsuarioDto registrar(UsuarioDto usuario) {

        if (this.usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailExistenteException("El email ingresado no esta disponible.");
        }

        usuario.setPassword(this.passwordManager.encode(usuario.getEmail()));
        Usuario u = this.usuarioRepository.save(modelMapper.map(usuario, Usuario.class));
        return this.modelMapper.map(u, UsuarioDto.class);
    }
}
