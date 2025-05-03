package com.starking.user_vendas.repositories;

import com.starking.user_vendas.model.SubPermissao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pedroRhamon
 */
public interface SubPermissaoRepository extends JpaRepository<SubPermissao, Long>{

//	List<SubPermissao> findByPermissaoPrincipal(Permissao permissaoPrincipal);

	Long findById(long id);

}
