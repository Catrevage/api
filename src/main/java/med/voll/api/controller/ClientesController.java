package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.clientes.Clientes;
import med.voll.api.clientes.ClientesRepository;
import med.voll.api.clientes.DadosAtualizacaoCliente;
import med.voll.api.clientes.DadosCadastroClientes;
import med.voll.api.clientes.DadosDetalharCliente;
import med.voll.api.clientes.DadosListagemClientes;

@RestController
@RequestMapping("pacientes")
public class ClientesController {
	
	@Autowired
	private ClientesRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroClientes dados, 
			UriComponentsBuilder uriBuilder ) {
		var cliente = new Clientes(dados); 
		repository.save(cliente);
		var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalharCliente(cliente));
		
	}
	
	
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemClientes>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemClientes::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalharCliente> detalhar(@PathVariable Long id){
		var cliente = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalharCliente(cliente));
	}
	
	@PutMapping
	@Transactional1
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		var cliente = repository.getReferenceById(dados.id());
		cliente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalharCliente(cliente));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var cliente = repository.getReferenceById(id);
		cliente.desativa();
		return ResponseEntity.noContent().build();
	}

}
