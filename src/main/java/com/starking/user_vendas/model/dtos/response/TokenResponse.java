package com.starking.user_vendas.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
	
	private String nome;
	private String token;

}
