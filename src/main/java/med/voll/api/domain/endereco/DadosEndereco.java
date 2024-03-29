package med.voll.api.domain.endereco;

import org.springframework.web.service.annotation.PatchExchange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		@NotBlank
		String rua,
		
		@NotBlank
		String bairro,
		
		@NotBlank @Pattern(regexp = "\\d{8}")
		String cep,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String uf,
		
		
		String numero,
		
		String complemento) 
{
	
}
