package med.voll.api.domain.clientes;

public record DadosListagemClientes(Long id, String nome, String email, String cpf) {
	
	public DadosListagemClientes(Clientes cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf());
		
	}

}
