package com.api.practica.ingreso;

import com.api.practica.commons.ModelMapperWrapper;
import com.api.practica.exceptions.CredencialesInvalidasException;
import com.api.practica.exceptions.CustomException;
import com.api.practica.exceptions.EmailExistenteException;
import com.api.practica.security.JwtTokenProvider;
import com.api.practica.usuario.Usuario;
import com.api.practica.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngresoBusiness {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapperWrapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public TokenDto autenticar(CredencialesDto usuario) {
        Optional<Usuario> u = this.usuarioRepository.findByEmail(usuario.getEmail());
        if (!u.isPresent()) {
            throw new CredencialesInvalidasException("Usuario invalido.");
        }

        Usuario usuarioBd = u.get();

        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
            String token =  jwtTokenProvider.createToken(usuarioBd.getEmail(), usuarioBd.getRoles());
            TokenDto response = new TokenDto();
            response.setToken(token);
            return response;

        } catch (AuthenticationException e) {

            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);

        }

    }



    public void registrar(CredencialesDto usuario) {

        if (this.usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailExistenteException("El email ingresado no esta disponible.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario u = this.usuarioRepository.save(modelMapper.map(usuario, Usuario.class));

    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtTokenProvider getJwtTokenProvider() {
        return jwtTokenProvider;
    }

    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ModelMapperWrapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapperWrapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
