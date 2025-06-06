package com.starking.user_vendas.services;

import com.starking.user_vendas.model.Permissao;
import com.starking.user_vendas.model.SubPermissao;
import com.starking.user_vendas.model.dtos.request.PermissaoRequest;
import com.starking.user_vendas.model.dtos.response.PermissaoResponse;
import com.starking.user_vendas.repositories.PermissaoRepository;
import com.starking.user_vendas.repositories.SubPermissaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    private final SubPermissaoRepository subPermissaoRepository;

    public Page<PermissaoResponse> listarTodos(Pageable pageable) {
		Page<Permissao> permissaoPage = permissaoRepository.findAll(pageable);
		return permissaoPage.map(PermissaoResponse::new);
	}
    
    @Transactional
    public PermissaoResponse criarPermissao(PermissaoRequest permissaoRequest) {
        // Busca as sub-permissões associadas pelos IDs
        List<SubPermissao> subPermissoes = subPermissaoRepository.findAllById(permissaoRequest.getSubPermissoes());

        Permissao permissao = new Permissao();
        permissao.setName(permissaoRequest.getName());
        permissao.setSubPermissoes(subPermissoes);

        Permissao permissaoSalva = permissaoRepository.save(permissao);

        return new PermissaoResponse(permissaoSalva);
    }

    public PermissaoResponse obterPermissaoPorId(Long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada com o ID: " + id));
        return new PermissaoResponse(permissao);
    }

    @Transactional
    public PermissaoResponse atualizarPermissao(Long id, PermissaoRequest permissaoRequest) {
        return permissaoRepository.findById(id).map(permissaoExistent -> {
            permissaoRequest.setName(permissaoExistent.getName());

            List<SubPermissao> subPermissoes = subPermissaoRepository.findAllById(permissaoRequest.getSubPermissoes());
            permissaoExistent.setSubPermissoes(subPermissoes);

            Permissao permissaoAtualizada = permissaoRepository.save(permissaoExistent);

            return new PermissaoResponse(permissaoAtualizada);
        }).orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada com o ID: " + id));
    }

    @Transactional
    public void deletarPermissao(Long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada com o ID: " + id));
        permissaoRepository.delete(permissao);
    }
}