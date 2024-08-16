package com.generation.alugaeanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.alugaeanda.models.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    public Optional<Usuario> findByUsuario(@Param("usuario") String usuario);

}
