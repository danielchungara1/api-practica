package com.api.practica.security;

import com.api.practica.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombre(String name);
}
