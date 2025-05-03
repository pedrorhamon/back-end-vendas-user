package com.starking.user_vendas.services;

import com.starking.user_vendas.model.Usuario;
import com.starking.user_vendas.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pedroRhamon
 */
@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = usuarioRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado"));
		
		 List<GrantedAuthority> authorities = usuarioEncontrado.getPermissoes()
	                .stream()
	                .map(permissao -> new SimpleGrantedAuthority("ROLE_" + permissao.getName()))
	                .collect(Collectors.toList());
		
		return User.builder()
				.username(usuarioEncontrado.getEmail())
				.password(usuarioEncontrado.getSenha())
				.authorities(authorities)
				.build();
	}
}
