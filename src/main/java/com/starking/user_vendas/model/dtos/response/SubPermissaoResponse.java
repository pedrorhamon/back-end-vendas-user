package com.starking.user_vendas.model.dtos.response;

import com.starking.user_vendas.model.SubPermissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubPermissaoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public SubPermissaoResponse(SubPermissao subPermissao) {
        this.id = subPermissao.getId();
        this.nome = subPermissao.getNome();
    }
}
