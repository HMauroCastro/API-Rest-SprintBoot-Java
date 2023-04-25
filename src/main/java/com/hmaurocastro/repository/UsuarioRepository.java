package com.hmaurocastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmaurocastro.model.Usuario;

/**
 * Spring implementa automáticamente esta interfaz de repositorio en un bean que
 * tiene el mismo nombre
 * 
 * @author HMauroCastro
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
