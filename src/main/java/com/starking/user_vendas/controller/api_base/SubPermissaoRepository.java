package com.starking.user_vendas.controller.api_base;

import com.starking.vendas.model.SubPermissao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pedroRhamon
 */
public interface SubPermissaoRepository extends JpaRepository<SubPermissao, Long>{

//	List<SubPermissao> findByPermissaoPrincipal(Permissao permissaoPrincipal);

	Long findById(long id);

}
