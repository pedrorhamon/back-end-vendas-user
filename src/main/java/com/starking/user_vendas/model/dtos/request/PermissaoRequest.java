package com.starking.user_vendas.model.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.starking.user_vendas.model.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissaoRequest  implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private Long id;
	private String name;
	private List<Long> subPermissoes;
    
	 public PermissaoRequest(Permissao permissao) {
		 	this.id = permissao.getId();
	        this.name = permissao.getName();
			this.subPermissoes = permissao.getSubPermissoes()
					.stream().map(sub -> sub.getId())
					.toList();
	    }
}