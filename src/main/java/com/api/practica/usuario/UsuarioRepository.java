package com.api.practica.usuario;

import com.api.practica.productos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    public Optional<Usuario> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    public Optional<Usuario> findByEmail(String email);

}
