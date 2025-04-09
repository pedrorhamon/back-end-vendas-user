package com.starking.user_vendas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starking.user_vendas.model.dtos.request.PermissaoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pedroRhamon
 */

@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 150)
	private String name;

	@NotNull
	@NotBlank
	@Email
	private String email;
	
	@NotEmpty
	@NotNull
	private String senha;
	
	@NotNull
	private Boolean ativo = true;
	
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	        name = "usuario_permissoes",
	        joinColumns = @JoinColumn(name = "usuario_id"),
	        inverseJoinColumns = @JoinColumn(name = "permissoes_id")
	    )
	private List<Permissao> permissoes;
	
	public List<PermissaoRequest> getPermissaoRequests() {
        return this.permissoes.stream()
                .map(PermissaoRequest::new)
                .collect(Collectors.toList());
    }
}
