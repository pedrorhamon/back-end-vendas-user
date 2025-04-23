package com.starking.user_vendas.repositories;

import com.starking.user_vendas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author pedroRhamon
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);


}