package med.voll.api.domain.clientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoCliente(
		@NotNull
		Long id, 
		String nome, 
		String telefone, 
		DadosEndereco endereco) {

}
