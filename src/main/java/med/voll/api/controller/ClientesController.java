package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.clientes.Clientes;
import med.voll.api.clientes.ClientesRepository;
import med.voll.api.clientes.DadosAtualizacaoCliente;
import med.voll.api.clientes.DadosCadastroClientes;
import med.voll.api.clientes.DadosListagemClientes;
import med.voll.api.medico.DadosAtualizacaoMedicos;
import med.voll.api.medico.DadosListagemMedicos;

@RestController
@RequestMapping("pacientes")
public class ClientesController {
	
	@Autowired
	private ClientesRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroClientes dados ) {
		repository.save(new Clientes(dados));
		
	}
	
	@GetMapping
	public Page<DadosListagemClientes> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemClientes::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		cliente.atualizarInformacoes(dados);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var cliente = repository.getReferenceById(id);
		cliente.desativa();
	}

}
