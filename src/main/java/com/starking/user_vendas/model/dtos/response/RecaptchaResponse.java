package com.starking.user_vendas.model.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@AllArgsConstructor
public class RecaptchaResponse {
	
	private boolean success;
    @JsonProperty("error-codes")
    private List<String> errorCodes;

}
