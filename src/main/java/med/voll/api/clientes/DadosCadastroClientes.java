package med.voll.api.clientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroClientes(
		
		@NotBlank
		String nome,
		
		@NotBlank @Email
		String email, 
		
		@NotBlank
		String telefone, 
		
		@NotBlank 
		String cpf, 
		
		@NotNull @Valid
		DadosEndereco endereco) {

}
