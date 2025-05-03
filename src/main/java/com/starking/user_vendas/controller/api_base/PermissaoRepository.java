package com.starking.user_vendas.controller.api_base;

import com.starking.vendas.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pedroRhamon
 */
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	Permissao findByName(String permissaoName);

	Long findById(long id);

}
