package med.voll.api.domain.consulta;


import jakarta.el.EvaluationListener;
import med.voll.api.domain.clientes.ClientesRepository;
import med.voll.api.domain.consulta.validacoes.Validacoes;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.domain.medico.Medico;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private List<Validacoes> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {
        if (!clientesRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente Não Existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Medico Não Existe");
        }

        validadores.forEach(value -> value.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = clientesRepository.findById(dados.idPaciente()).get();


        Consulta consulta;
        consulta = new Consulta(null, medico, paciente, dados.data());

        repository.save(consulta);
    }

    public Consulta cancelar(DadosCancelamento dados){

        var consulta = repository.getReferenceById(dados.id());

       if(Duration.between(LocalDateTime.now(), consulta.getData()).toHours() > 24){
           consulta.cancelarConsulta(dados);
           return repository.getReferenceById(consulta.getId());
       } else {
           throw new ValidacaoException("Cancelamento inválido");
       }

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());

        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatório");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
