package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.clientes.ClientesRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteInativo implements Validacoes {
    @Autowired
    private ClientesRepository  repository;
    public void validar(DadosAgendamentoConsulta dados){
                var cliente = repository.getReferenceById(dados.idPaciente());
                if (!cliente.isAtivo()){
                    throw new ValidacaoException("O Paciente Está inativo!");
                }
    }
}
