package com.starking.user_vendas.controller;

import com.starking.user_vendas.controller.api_base.ApiPermissaoBaseControle;
import com.starking.user_vendas.event.RecursoCriadoEvent;
import com.starking.user_vendas.model.dtos.request.PermissaoRequest;
import com.starking.user_vendas.model.dtos.response.PermissaoResponse;
import com.starking.user_vendas.services.PermissaoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author pedroRhamon
 */
@RestController
@AllArgsConstructor
public class PermissaoResource extends ApiPermissaoBaseControle {
	
	private final PermissaoService permissaoService;
	
	private final ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<Page<PermissaoResponse>> listar(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@PageableDefault(size = 10) Pageable pageable) {

		Page<PermissaoResponse> permissoes = permissaoService.listarTodos(pageable);
		return !permissoes.isEmpty() ? ResponseEntity.ok(permissoes)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
	}

	@PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PermissaoRequest permissaoRequest, HttpServletResponse response) {
        try {
        	PermissaoResponse permissaoNew = this.permissaoService.criarPermissao(permissaoRequest);
            
            publisher.publishEvent(new RecursoCriadoEvent(this, response, permissaoNew.getId()));
            return ResponseEntity.status(HttpStatus.CREATED).body(permissaoNew);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar a Permissão.");
        }
    }

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody PermissaoRequest permissaoRequest) {
		try {
			// Atualiza a permissão com base no ID e nos dados fornecidos
			PermissaoResponse permissaoAtualizada = this.permissaoService.atualizarPermissao(id, permissaoRequest);

			return ResponseEntity.ok(permissaoAtualizada); // Retorna o objeto atualizado com status 200 OK
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão não encontrada: " + e.getMessage());
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar a Permissão.");
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<PermissaoResponse> getPessoaById(@PathVariable Long id) {
		PermissaoResponse permissaoResponse = permissaoService.obterPermissaoPorId(id);
		return ResponseEntity.ok(permissaoResponse);
	}
}
