package com.starking.user_vendas.model.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pedroRhamon
 */
@Data
@NoArgsConstructor
public class CredenciaisRequest {
	
	private String email;
	private String senha;
	private String recaptchaResponse;

}
