package com.starking.user_vendas.model.dtos.response;

import com.starking.user_vendas.model.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoResponse implements Serializable{
	
    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<SubPermissaoResponse> subPermissoes;

	public PermissaoResponse(Permissao permissao) {
		this.id = permissao.getId();
		this.name = permissao.getName();
		this.subPermissoes = permissao.getSubPermissoes().stream()
				.map(SubPermissaoResponse::new)
				.collect(Collectors.toList());
	}
}
