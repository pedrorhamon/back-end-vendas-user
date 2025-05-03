package com.starking.user_vendas.repositories;

import com.starking.user_vendas.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pedroRhamon
 */
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	Permissao findByName(String permissaoName);

	Long findById(long id);

}
