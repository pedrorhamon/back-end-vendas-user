package com.starking.user_vendas.model.dtos.response;

import com.starking.user_vendas.model.Permissao;
import com.starking.user_vendas.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@NotBlank
	@Size(min = 5, max = 150)
    private String name;
    
	@NotNull
	@NotBlank
    private String email;
    
    private Boolean ativo = true;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private String createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private String updatedAt;
	
    private List<String> permissoes;
	
	public UsuarioResponse(Usuario entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.ativo = entity.getAtivo();
		this.createdAt = entity.getCreatedAt() != null ? entity.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : null;
		this.updatedAt = entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : null;
		this.permissoes = entity.getPermissoes() != null 
			    ? entity.getPermissoes().stream().map(Permissao::getName).collect(Collectors.toList())
			    : Collections.emptyList();
	}
}
