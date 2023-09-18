package med.voll.api.clientes;

import med.voll.api.endereco.Endereco;

public record DadosDetalharCliente(
		Long id,
		String nome,
		String email,
		String telefone,
		String cpf,
		boolean ativo,
		Endereco endereco
		) {
	
	public DadosDetalharCliente(Clientes cliente) {
		this(
				cliente.getId(), 
				cliente.getNome(),
				cliente.getEmail(),
				cliente.getTelefone(),
				cliente.getCpf(),
				cliente.isAtivo(),
				cliente.getEndereco()
				);
	}
}
